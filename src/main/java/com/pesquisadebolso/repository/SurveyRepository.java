package com.pesquisadebolso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pesquisadebolso.model.Survey;

@Repository
public interface SurveyRepository extends JpaRepository <Survey, Integer> {
 
    public List<Survey> findByCompanyId(final Integer idCompany);

    public List<Survey> findByLink(final String encryptedLink);

}