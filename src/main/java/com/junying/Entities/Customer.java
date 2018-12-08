package com.junying.Entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Long customerId;

    private String name;
    private String address;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Account> accounts;


    public Customer() {
    }

    public Customer(Long customerId, String name, String address, String email, List<Account> accounts,String password) {
        this.customerId =customerId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
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
