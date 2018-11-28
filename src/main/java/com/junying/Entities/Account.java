package com.junying.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account",schema = "public")

public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long accountId;

    private String sort_code;

    private int balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "customer_id")
    private Long customerId;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;


    public Account(String sort_code, int balance, Long customerId, String accountType, List<Transaction> transactions) {
        this.sort_code = sort_code;
        this.balance = balance;
        this.customerId = customerId;
        this.accountType = accountType;
        this.transactions = transactions;
    }

    public Account() {
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSort_code() {
        return sort_code;
    }

    public void setSort_code(String sort_code) {
        this.sort_code = sort_code;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
