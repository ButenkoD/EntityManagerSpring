package com.andersenlab.entity_manager_spring.entity;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository extends AbstractRepository {
    private static final String CANT_FOUND_MESSAGE = "Can't found customer with id ";
    public String create(List<String> params) throws Exception {
        Customer customer = new Customer(params);
        List<Customer> result = create(customer, Customer.class);
        return objectsToString(result);
    }

    public String remove(List<String> params) throws Exception {
        if (params.size() == 1) {
            return remove(Integer.parseInt(params.get(0)));
        }
        throw new IllegalArgumentException();
    }

    Customer findById(int id) throws Exception {
        Customer customer = (Customer) find(Customer.class, id);
        if (customer == null) {
            throw new NotFoundException(CANT_FOUND_MESSAGE + Integer.toString(id));
        }
        return customer;
    }

    private String remove(int id) throws Exception {
        remove(Customer.class, id);
        return "Customer was removed";
    }

    public String show() throws Exception {
        return objectsToString(selectAll(Customer.class));
    }
}
