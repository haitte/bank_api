package com.junying.Daos;

import com.junying.Dtos.Credentials;
import com.junying.Entities.Customer;
import com.junying.Exceptions.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;
import java.util.List;


public class CustomerDao implements Dao<Customer>{

    EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

        //login
    public Long login(Credentials credentials){
        TypedQuery<Customer> query = entityManager.createQuery("SELECT customer FROM Customer customer WHERE email=?1",Customer.class);
        query.setParameter(1,credentials.getEmail());
        Customer customer = query.getSingleResult();
        if(BCrypt.checkpw(credentials.getPassword(),customer.getPassword())){
            //200
            return  customer.getCustomerId();
        }else{
            //403
            return null;
        }
    }

    //register
    public Long register(Customer customer){
        customer.setPassword( BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt()));

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            entityManager.close();
            //200
            return  customer.getCustomerId();
    }


    public Customer getCustomer(Long id){
        Customer customer = entityManager.find(Customer.class,id);
        if(customer == null){
            throw new NotFoundException("This customer id do not exist.");
        }
        return customer;
    }

    public List<String> getCustomerName(){
        TypedQuery<String> query = entityManager.createQuery("SELECT name FROM Customer ",String.class);
        return  query.getResultList();
    }

    public List<Customer> getLogin( String username, String password){
        Query query = entityManager.createNativeQuery("SELECT * FROM Customer WHERE name=?1 ",Customer.class);
        query.setParameter(1, username);
        List<Customer> customer = query.getResultList();

        return  null;
    }

    @Override
    public Customer get(long id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM customer;",Customer.class);
        List<Customer> customer = query.getResultList();
        return  customer;
    }

    @Override
    public void save(Customer customer) {
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
