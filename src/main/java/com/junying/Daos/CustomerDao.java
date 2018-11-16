package com.junying.Daos;

import com.junying.Entities.Customer;

import javax.persistence.EntityManager;

public class CustomerDao {

    EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Customer getCustomer(Long id){
        Customer customer = entityManager.find(Customer.class,id);
        return customer;
    }

}
