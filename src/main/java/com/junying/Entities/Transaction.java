package com.junying.Entities;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    Long transactionId;
    private String date;
    private String type;
    private int amount;
    private int post_transaction;
    private String description;
    @Column(name = "account_id")
    private int accountId;

    public Transaction() {
    }

    public Transaction(String date, String type, int amount, int post_transaction, String description, int accountId) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.post_transaction = post_transaction;
        this.description = description;
        this.accountId = accountId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPost_transaction() {
        return post_transaction;
    }

    public void setPost_transaction(int post_transaction) {
        this.post_transaction = post_transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
