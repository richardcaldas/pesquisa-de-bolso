package com.pesquisadebolso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pesquisadebolso.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    
}