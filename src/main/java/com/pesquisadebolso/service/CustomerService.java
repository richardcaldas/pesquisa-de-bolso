package com.pesquisadebolso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pesquisadebolso.model.Customer;
import com.pesquisadebolso.repository.CustomerRepository;

/**
 * CustomerService
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomersByIdCompany(final Integer idCompany){
        return customerRepository.findByCompanyId(idCompany);
    }

    @Transactional
	public Customer create(Customer customer) {
        customer.getUser().setRole("CT");
		return customerRepository.save(customer);
	}
    
}