package com.pesquisadebolso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "SURVEYS")
public class Survey {

    private static final String WAIT = "WAIT";
    private static final String OPEN = "OPEN";
    private static final String CLOSED = "CLOSED";
    
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "START_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Brazil/East", locale = "pt-BR")
    private Date start;

    @Column(name = "END_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "Brazil/East", locale = "pt-BR")
    private Date end;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "LINK")
    private String link;

    @Column(name = "GRADE_NPS")
    private BigDecimal gradeNPS;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private List<Answer> answerList;

    @Transient
    private String status;

    @Transient
    private int answerQuantity;

    public int getAnswerQuantity(){
        if (this.getAnswerList() == null){
            return 0;
        }else{
            return this.getAnswerList().size();
        }
    }

    public String getStatus(){
        if (this.getStart().compareTo(new Date()) > 0){
            return Survey.WAIT;
        }else if (this.getStart().compareTo(new Date()) <= 0 && this.getEnd().compareTo(new Date()) >= 0){
            return Survey.OPEN;
        }else{
            return Survey.CLOSED;
        }
    }

    public BigDecimal getGradeNPS(){
        if (this.getAnswerList() == null || this.getAnswerList().size() == 0){
            return null;
        }else{
            final BigDecimal totalAnswers = new BigDecimal(this.getAnswerList().size());
            final BigDecimal promotersQuantity = new BigDecimal(this.getAnswerList().stream()
                                                    .filter(a -> a.getGrade() >= 9)
                                                    .count());

            final BigDecimal detractorsQuantity = new BigDecimal(this.getAnswerList().stream()
                                                    .filter(a -> a.getGrade() <= 6)
                                                    .count());
            
            return promotersQuantity.subtract(detractorsQuantity).divide(totalAnswers, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));

        }
    } 
    
}