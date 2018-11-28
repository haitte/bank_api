package com.junying;

import com.junying.Daos.AccountDao;
import com.junying.Daos.CustomerDao;
import com.junying.Entities.Account;
import com.junying.Entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("bank")
public class MyResource {

//    @PersistenceContext(unitName = "awesomebankunit")
//    PersistenceContext persistenceContext;

    public static final String PERSISTENCE_UNIT_NAME = "awesomebankunit";

    EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();

    private CustomerDao cDao = new CustomerDao(em);
    private AccountDao aDao;

    public MyResource() {
        aDao = new AccountDao(em);
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Path("/description/customer/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") Long id){
        ArrayList<Account> customerList = new ArrayList<>();

        Customer customer = this.cDao.getCustomer(id);
        return customer;
    }
    @GET
    @Path("/description/customer/account/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("id") Long id){
        Account account = this.aDao.get(id);
        return account;
    }

    @GET
    @Path("/description/allAccounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllCustomers() {
        List<Account> accounts = this.aDao.getAll();
        return accounts;
    }
}
