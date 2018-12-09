package com.junying.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long accountId;

    @Column(name = "sort_code")
    private String sortCode;

    private float balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "customer_id")
    private Long customerId;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;


    public Account(Long accountId, String sortCode, float balance, Long customerId, String accountType, List<Transaction> transactions) {
        this.accountId = accountId;
        this.sortCode = sortCode;
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

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
