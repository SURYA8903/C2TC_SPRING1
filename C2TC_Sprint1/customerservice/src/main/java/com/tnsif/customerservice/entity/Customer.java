package com.tnsif.customerservice.entity;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String orderId;
    private String phone;
    private String email;
    private String address;
    private String city;

    @Version
    private Integer version;

    // Default constructor
    public Customer() {
        super();
    }

    // Parameterized constructor
    public Customer(Long id, String name, String orderId, String phone, String email, String address, String city) {
        super();
        this.id = id;
        this.name = name;
        this.orderId = orderId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer [CustomerId=" + id + ", name=" + name + ", OrderId=" + orderId +
               ", email=" + email + ", phoneNumber=" + phone + ", address=" + address + ", city=" + city + "]";
    }
}
