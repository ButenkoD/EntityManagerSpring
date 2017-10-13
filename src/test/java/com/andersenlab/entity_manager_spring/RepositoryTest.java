package com.andersenlab.entity_manager_spring;

import com.andersenlab.entity_manager_spring.entity.CustomerRepository;
import com.andersenlab.entity_manager_spring.entity.ProductRepository;
import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class RepositoryTest {
    private CustomerRepository repository;

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerRepository repository = new CustomerRepository();
        ArrayList<String> params = new ArrayList<>();
        params.add("NewlyCreated");
        params.add("Customer");
        String result = repository.create(params);
        assertThat(result, containsString("Name: NewlyCreated"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceprionOnCreateCustomer() throws Exception
    {
        CustomerRepository repository = new CustomerRepository();
        ArrayList<String> params = new ArrayList<>();
        params.add("NewlyCreated");
        params.add("Customer");
        params.add("ExtraField");
        repository.create(params);
    }

    @Test
    public void testCreateProduct() throws Exception {
        ProductRepository repository = new ProductRepository();
        ArrayList<String> params = new ArrayList<>();
        params.add("NewProduct");
        params.add("198");
        String result = repository.create(params);
        assertThat(result, containsString("Price: 198"));
    }
}
