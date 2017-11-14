package com.andersenlab.entity_manager_spring.entity;

import com.andersenlab.entity_manager_spring.ConsoleApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseRepository extends AbstractRepository {
    private static final String CANT_CREATE_MESSAGE = "Can't create purchase, "
            + "expected minimum 2 parameters: customer_id and product_id_1 [product_id_2 ...] \n";

    public String create(List<String> params) throws Exception {
        Purchase purchase = new Purchase();
        if (params.size() > 1) {
            int customerId = Integer.parseInt(params.get(0));
            Customer customer = ConsoleApplication.getContext().getBean(CustomerRepository.class)
                    .findById(customerId);
            customer.addPurchase(purchase);
            List<Integer> productIds = new ArrayList<>();
            for (String productId: params.subList(1, params.size())) {
                productIds.add(Integer.parseInt(productId));
            }
            List<Product> products = ConsoleApplication.getContext().getBean(ProductRepository.class)
                    .findAllByIds(productIds);
            purchase.setProducts(products);
            return objectsToString(create(purchase, Purchase.class));
        }
        throw new Exception(CANT_CREATE_MESSAGE);
    }

    public String remove(List<String> params) throws Exception {
        if (params.size() == 1) {
            return remove(Integer.parseInt(params.get(0)));
        }
        throw new IllegalArgumentException();
    }

    private String remove(int id) throws Exception {
        remove(Purchase.class, id);
        return "Purchase was removed";
    }

    public String show() throws Exception {
        return objectsToString(selectAll(Purchase.class));
    }
}
