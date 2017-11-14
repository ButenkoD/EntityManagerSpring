package com.andersenlab.entity_manager_spring.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository extends AbstractRepository {
    public String create(List<String> params) throws Exception {
        Product product = new Product(params);
        List<Product> result = create(product, Product.class);
        return objectsToString(result);
    }

    public String remove(List<String> params) throws Exception {
        if (params.size() == 1) {
            return remove(Integer.parseInt(params.get(0)));
        }
        throw new IllegalArgumentException();
    }

    private String remove(int id) throws Exception {
        remove(Product.class, id);
        return "Product was removed";
    }

    public String show() throws Exception {
        return objectsToString(selectAll(Product.class));
    }

    public List<Product> findAllByIds(List<Integer> ids) throws Exception {
        return findAllByIds(Product.class, ids);
    }
}
