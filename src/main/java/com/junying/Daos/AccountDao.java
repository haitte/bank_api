package com.junying.Daos;

import com.junying.Entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AccountDao implements Dao<Account> {

    EntityManager entityManager;

    public AccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Account get(long id) {
        Account account = entityManager.find(Account.class, id);
        return account;
    }

    @Override
    public List<Account> getAll() {

        Query query = entityManager.createNativeQuery("SELECT * FROM account;",Account.class);
        List<Account> accounts = query.getResultList();
        return  accounts;
    }

    @Override
    public void save(Account account) {

    }

    @Override
    public void update(Account account, String[] params) {

    }

    @Override
    public void delete(Account account) {

    }


}
