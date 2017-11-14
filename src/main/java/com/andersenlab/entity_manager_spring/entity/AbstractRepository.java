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

    <T> String objectsToString(List<T> objects) {
        if (objects.isEmpty()) {
            return "Empty table";
        }
        StringBuilder stringBuilder = new StringBuilder();
        objects.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }


    <T> List<T> selectAll(Class<T> clazz) {
        return entityManager.createQuery("select c from "+clazz.getSimpleName()+" c", clazz).getResultList();
    }


    public <T> List<T> create(T object, Class<T> clazz) {
        entityManager.persist(object);
        return selectAll(clazz);
    }

    <T> void remove(Class<T> clazz, int id) {
        Object object = entityManager.find(clazz, id);
        entityManager.remove(object);
    }


    <T> T find(Class<T> clazz, int id) throws Exception  {
        return entityManager.find(clazz, id);
    }

    <T> List<T> findAllByIds(Class<T> clazz, List<Integer> ids) throws Exception{
        return entityManager.createQuery(
                "SELECT p FROM "
                        + clazz.getSimpleName()
                        +" p WHERE p.id IN :ids", clazz)
                .setParameter("ids", ids).getResultList();
    }
}
