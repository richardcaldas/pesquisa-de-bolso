package com.pesquisadebolso.service;

import java.util.List;

import com.pesquisadebolso.model.Company;
import com.pesquisadebolso.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company create(final Company company) {
        company.getUser().setRole("CP");
        return companyRepository.save(company);
    }

}