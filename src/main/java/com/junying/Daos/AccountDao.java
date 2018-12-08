package com.junying.Daos;

import com.junying.Entities.Account;
import com.junying.Exceptions.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AccountDao implements Dao<Account> {

    EntityManager entityManager;

    public AccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public int getBalance(Long accountId){
        return entityManager.find(Account.class,accountId).getBalance();
    }

    @Override
    public Account get(long id) {
        Account account = entityManager.find(Account.class, id);
        if(account == null){
            throw new NotFoundException("This account id do not exist.");
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM account;",Account.class);
        List<Account> accounts = query.getResultList();
        return  accounts;
    }

    @Override /* create account*/
    public void save(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        System.out.println(account.getAccountId());
        entityManager.close();
    }

    @Override
    public void update(Account account) {
        entityManager.getTransaction().begin();
        entityManager.merge(account);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Account account) {

    }


}
