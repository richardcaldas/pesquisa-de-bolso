package com.pesquisadebolso.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ANSWERS")
public class Answer {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ANSWER_DATE", nullable = false)
    private Date answerDate;
    
    @Column(name = "GRADE", nullable = false)
    private int grade;

    @ManyToOne
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;

    @OneToOne
    @JoinColumn(name = "CUSTOMERS_ID")
    private Customer customer;
    
}