package com.pesquisadebolso.controller;

import java.util.List;

import com.pesquisadebolso.model.Company;
import com.pesquisadebolso.model.Customer;
import com.pesquisadebolso.service.CompanyService;
import com.pesquisadebolso.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;
    
    @GetMapping(value="/findAll")
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }
    

    @PostMapping(value="/create")
    public ResponseEntity<Company> postMethodName(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    @GetMapping(value="/get-customers/{idCompany}")
    public ResponseEntity<List<Customer>> getCustomersByCompany(@PathVariable Integer idCompany) {
        return ResponseEntity.ok(customerService.getCustomersByIdCompany(idCompany));
    }

    @PostMapping(value="/add-customer")
    public ResponseEntity<Customer> getCustomersByCompany(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

}