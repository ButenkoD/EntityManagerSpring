package com.andersenlab.entity_manager_spring.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class AbstractRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private List<String> accessibleMethods;

    public boolean hasAccessibleMethod(String methodName) {
        return getAccessibleMethods().contains(methodName);
    }

    private List<String> getAccessibleMethods() {
        if (accessibleMethods == null) {
            accessibleMethods = new ArrayList<>();
            for (Method method : this.getClass().getDeclaredMethods()) {
                if (isPublicNonStaticMethod(method))
                    accessibleMethods.add(method.getName());
            }
        }
        return accessibleMethods;
    }

    private static boolean isPublicNonStaticMethod(Method method) {
        int mod = method.getModifiers();
        return !Modifier.isStatic(mod) && Modifier.isPublic(mod);
    }

    String objectsToString(List<?> objects) {
        if (objects.isEmpty()) {
            return "Empty table";
        }
        StringBuilder stringBuilder = new StringBuilder();
        objects.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }


    List selectAll(String classname) {
        return entityManager.createQuery("select c from "+classname+" c", Object.class).getResultList();
    }


    public List create(Object object) {
        entityManager.persist(object);
        return selectAll(object.getClass().getSimpleName());
    }

    void remove(Class<?> classObject, int id) {
        Object object = entityManager.find(classObject, id);
        entityManager.remove(object);
    }


    Object find(Class<?> classObject, int id) throws Exception  {
        return entityManager.find(classObject, id);
    }

    List<?> findAllByIds(Class<?> classObject, List<Integer> ids) throws Exception{
        return entityManager.createQuery(
                "SELECT p FROM "
                        + classObject.getSimpleName()
                        +" p WHERE p.id IN :ids", classObject)
                .setParameter("ids", ids).getResultList();
    }
}
