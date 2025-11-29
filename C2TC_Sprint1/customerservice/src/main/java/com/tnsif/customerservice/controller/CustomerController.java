package com.tnsif.customerservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.tnsif.customerservice.entity.Customer;
import com.tnsif.customerservice.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerservice")
public class CustomerController {

    @Autowired
    private CustomerService service;

    // Simple test endpoint
    @GetMapping("/")
    public String send() {
        return "Hello from MohanKumar";
    }

    // Get all customers
    @GetMapping
    public List<Customer> listAll() {
        return service.listAll();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        try {
            Customer customer = service.get(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add new customer
    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        Customer savedCustomer = service.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Update existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@RequestBody Customer customer, @PathVariable Long id) {
        try {
            Customer existing = service.get(id);
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setAddress(customer.getAddress());
            existing.setPhone(customer.getPhone());
            existing.setCity(customer.getCity());
            existing.setOrderId(customer.getOrderId());

            Customer updated = service.save(existing);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete customer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
