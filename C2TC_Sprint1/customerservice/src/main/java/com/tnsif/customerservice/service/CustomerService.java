package com.tnsif.customerservice.service;

import com.tnsif.customerservice.entity.Customer;
import com.tnsif.customerservice.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    // Get all customers
    public List<Customer> listAll() {
        return repo.findAll();
    }

    // Save or update customer
    public Customer save(Customer customer) {
        if (customer.getId() != null) {
            // Check if existing customer is found
            Customer existing = repo.findById(customer.getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customer.getId()));

            // Update existing customer fields
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());
            existing.setAddress(customer.getAddress());
            existing.setPhone(customer.getPhone());

            return repo.save(existing);
        } else {
            // New customer
            return repo.save(customer);
        }
    }

    // Get customer by ID
    public Customer get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    // Delete customer by ID
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cannot delete. Customer not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
