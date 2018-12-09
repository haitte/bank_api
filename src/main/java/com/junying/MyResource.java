package com.junying;

import com.junying.Daos.AccountDao;
import com.junying.Daos.CustomerDao;
import com.junying.Daos.TranscationDao;
import com.junying.Dtos.Credentials;
import com.junying.Entities.Account;
import com.junying.Entities.Customer;
import com.junying.Entities.Transaction;
import com.junying.Exceptions.BalanceTooLowException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private TranscationDao tDao = new TranscationDao(em);

    public MyResource() {
        aDao = new AccountDao(em);
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

//  details with one customer
    @GET
    @Path("/description/customer/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Customer getCustomer(@PathParam("id") Long id){
        ArrayList<Account> customerList = new ArrayList<>();
//        Customer c= this.cDao.getCustomer(id);
        return this.cDao.getCustomer(id);
    }

//    details with one account
    @GET
    @Path("/description/customer/account/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Account getAccount(@PathParam("id") Long id){
//        Account a = this.aDao.get(id);
        return this.aDao.get(id);
    }

//    All accounts
    @GET
    @Path("/description/allAccounts")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Account> getAllAccounts() {
        List<Account> accounts = this.aDao.getAll();
        return accounts;
    }

//    All customers
    @GET
    @Path("/description/allCustomers")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Customer> getAllCustomers() {
        return this.cDao.getAll();
    }

//    All transactions
    @GET
    @Path("/description/allTransactions")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Transaction> getAllTransactions() {
        return this.tDao.getAll();
    }

//    All customers' name
    @GET
    @Path("/description/allCustomersName")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<String> getAllCustomersName() {
        return this.cDao.getCustomerName();
    }

//    one balance
    @GET
    @Path("/description/balance/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getBalance(@PathParam("id") Long id) {
        float balance =this.aDao.getBalance(id);
        return Response.status(200).entity(balance).build();
    }

//    one transaction
    @GET
    @Path("/description/transaction/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Transaction getT(@PathParam("id") Long id){
        return this.tDao.get(id);
    }

//  get all records of {} type= credit or debit -eg .http://localhost:8080/api/bank/description/transaction?type=credit
    @GET
    @Path("/description/transaction")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Transaction> getType(@QueryParam("type") String type){
        return this.tDao.getType(type);
    }

    //    login
    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response login(Credentials credentials){
        return this.cDao.login(credentials);
    }

    //    register
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response register(Customer customer){
        return this.cDao.register(customer);
    }


    //    create a new account
    @POST
    @Path("/createAccount")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response createAccount(Account account){
        this.aDao.save(account);
        return Response.status(201).build();
    }

//    create a lodgement
    @POST
    @Path("/credit")
    @Consumes({ MediaType.APPLICATION_JSON })
    public void lodgement(Transaction transaction) throws BalanceTooLowException {
        Transaction transaction1 = this.tDao.movement(transaction);
        Account account = this.aDao.get(transaction1.getAccountId());
        account.setBalance(transaction1.getPostTransaction());
        this.aDao.update(account);
    }

//    create a transfer http://localhost:8080/api/bank/transfer/11/12?amount=100&description=vel
    @POST
    @Path("/transfer/{id}/{anotherId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public void transfer(@PathParam("id") Long id,
                         @QueryParam("amount") int amount,
                         @QueryParam("description") String description,
                         @PathParam("anotherId") Long anotherId) throws BalanceTooLowException{
        this.tDao.transfer(id,amount,description,anotherId);

    }

//    create a withdrawal
    @POST
    @Path("/withdrawal")
    @Consumes({ MediaType.APPLICATION_JSON })
    public void withdrawal(Transaction transaction) throws BalanceTooLowException {
        Transaction transaction1 = this.tDao.movement(transaction);
        Account account = this.aDao.get(transaction1.getAccountId());
        account.setBalance(transaction1.getPostTransaction());
        this.aDao.update(account);
    }

//    @POST
}
