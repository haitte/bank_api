package com.junying.Entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@Entity
@Table(name = "transaction")
@XmlRootElement
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    Long transactionId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    private String type;
    private float amount;
    @Column(name = "post_transaction")
    private float postTransaction;
    private String description;

    @Column(name = "account_id")
    private Long accountId;

//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private List<Account> accounts;

    public Transaction() {
    }

    public Transaction(Long transactionId, Date date, String type, int amount, float postTransaction, String description, Long accountId) {
        this.transactionId = transactionId;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.postTransaction = postTransaction;
        this.description = description;
        this.accountId = accountId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPostTransaction() {
        return postTransaction;
    }

    public void setPostTransaction(float post_transaction) {
        this.postTransaction = post_transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
