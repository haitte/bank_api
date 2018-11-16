package com.junying.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Long customerId;

    private String name;
    private String address;
    private String email;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Account> accounts;


    public Customer() {
    }

    public Customer(String name, String address, String email, List<Account> accounts) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.accounts = accounts;
    }
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
