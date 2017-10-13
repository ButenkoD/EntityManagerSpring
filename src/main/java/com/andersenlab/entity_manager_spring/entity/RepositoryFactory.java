package com.andersenlab.entity_manager_spring.entity;

import com.andersenlab.entity_manager_spring.ConsoleApplication;

public class RepositoryFactory {
    private static final String CANT_FIND_ENTITY_MESSAGE = "Can't find entity ";
    public static AbstractRepository createRepository(String modelName) throws Exception {
        switch (modelName) {
            case "customer":
                return ConsoleApplication.getContext().getBean(CustomerRepository.class);
            case "product":
                return ConsoleApplication.getContext().getBean(ProductRepository.class);
            case "purchase":
                return ConsoleApplication.getContext().getBean(PurchaseRepository.class);
            default:
                throw new IllegalArgumentException(CANT_FIND_ENTITY_MESSAGE + modelName);
        }
    }
}
