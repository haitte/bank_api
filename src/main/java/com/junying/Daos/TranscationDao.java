package com.junying.Daos;

import com.junying.Entities.Account;
import com.junying.Entities.Transaction;
import com.junying.Exceptions.BalanceTooLowException;
import com.junying.Exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class TranscationDao implements Dao<Transaction> {
    EntityManager entityManager;

    public TranscationDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Transaction get(long id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        if(transaction == null){
            throw new NotFoundException("This transaction id do not exist.");
        }
        return transaction;
    }

    public Transaction movement(Transaction transaction) {

        int postTransaction=0;
        if(transaction.getType().equals("credit")){
            postTransaction = entityManager.find(Account.class,transaction.getAccountId()).getBalance()+transaction.getAmount();
        }else if(transaction.getType().equals("debit")){
            postTransaction = entityManager.find(Account.class,transaction.getAccountId()).getBalance()-transaction.getAmount();
        }
        if (postTransaction<0){
            throw new BalanceTooLowException("Balance too low.");
        }
        transaction.setPostTransaction(postTransaction);
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        return transaction;
    }

    public void transfer(Long id, int amount ,String description, Long anotherId) {
        int postTransaction1=0;
        int postTransaction2=0;
            postTransaction1 = entityManager.find(Account.class,id).getBalance()-amount;
            if (postTransaction1<0){
                throw new BalanceTooLowException("Balance too low, you can not make this transfer.");
            }
            postTransaction2 = entityManager.find(Account.class,anotherId).getBalance()+amount;

        Transaction transaction1 =  new Transaction();
        Transaction transaction2 =  new Transaction();

        transaction1.setPostTransaction(postTransaction1);
        transaction1.setType("debit");
        transaction1.setAmount(amount);
        transaction1.setDescription(description);
        transaction1.setAccountId(id);

        transaction2.setPostTransaction(postTransaction2);
        transaction2.setType("credit");
        transaction2.setAmount(amount);
        transaction2.setDescription("From account id "+entityManager.find(Account.class,anotherId).getCustomerId());
        transaction2.setAccountId(anotherId);

        Account account1 = entityManager.find(Account.class,id);
        account1.setAccountId(id);
        account1.setSortCode(account1.getSortCode());
        account1.setBalance(postTransaction1);
        account1.setCustomerId(account1.getCustomerId());
        account1.setAccountType(account1.getAccountType());

        Account account2 = entityManager.find(Account.class,anotherId);
        account2.setAccountId(anotherId);
        account2.setSortCode(account2.getSortCode());
        account2.setBalance(postTransaction2);
        account2.setCustomerId(account2.getCustomerId());
        account2.setAccountType(account2.getAccountType());

        entityManager.getTransaction().begin();
        entityManager.persist(transaction1);
        entityManager.persist(transaction2);
        entityManager.merge(account1);
        entityManager.merge(account2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Transaction> getType(String type){
        Query query = entityManager.createNativeQuery("SELECT * FROM transaction WHERE type=?1",Transaction.class);
        query.setParameter(1, type);
        return  query.getResultList();
    }

    @Override
    public List<Transaction> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM transaction;",Transaction.class);
        List<Transaction> transaction = query.getResultList();
        return  transaction;
    }

    @Override
    public void save(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        System.out.println(transaction.getTransactionId());
    }

    @Override
    public void update(Transaction transaction) {

    }

    @Override
    public void delete(Transaction transaction) {

    }
}
