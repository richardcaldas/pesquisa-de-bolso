package com.pesquisadebolso.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "COMPANIES")
public class Company {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "CPF_CNPJ", nullable = false)
    private String cpfCnpj;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "BRAND_IMG")
    private String brandImage;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Survey> surveyList;

    @Transient
    private int surveyQuantity;

    @OneToMany (mappedBy = "company")
    @JsonIgnore
    private List<Customer> listCustomers;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    public int getSurveyQuantity(){
        if (this.getSurveyList() == null){
            return 0;
        }else{
            return this.getSurveyList().size();
        }
    }
    
}