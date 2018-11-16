package com.junying;

import com.junying.Daos.AccountDao;
import com.junying.Daos.CustomerDao;
import com.junying.Entities.Account;
import com.junying.Entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    CustomerDao dao = new CustomerDao(em);
    AccountDao Adao;

    public MyResource() {
        Adao = new AccountDao(em);
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Path("/description/customerId5")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(){
        ArrayList<Account> customerList = new ArrayList<>();

        Customer customer = this.dao.getCustomer(5L);
        return customer;
    }

    @GET
    @Path("/description/allAccounts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllCustomers() {
        List<Account> accounts = this.Adao.getAll();
        return accounts;
    }
}
