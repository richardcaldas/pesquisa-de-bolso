package com.pesquisadebolso.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pesquisadebolso.model.Answer;
import com.pesquisadebolso.model.Company;
import com.pesquisadebolso.model.Customer;
import com.pesquisadebolso.model.Survey;
import com.pesquisadebolso.model.User;
import com.pesquisadebolso.repository.AnswerRepository;
import com.pesquisadebolso.repository.CustomerRepository;
import com.pesquisadebolso.repository.SurveyRepository;
import com.pesquisadebolso.utils.EncryptorUtils;

@Service
public class SurveyService {
    
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Survey> findByCompany(final Integer idCompany) {
        return surveyRepository.findByCompanyId(idCompany);
    }

    public Survey findById(final int id){
        return surveyRepository.findById(id).orElse(null);
    }

    public Survey create(final Survey survey){
        survey.setCreateDate(new Date());

        final Survey surveyAfterUpdate = surveyRepository.save(survey); 

        final String link = "idPesquisa=" + surveyAfterUpdate.getId() 
                            + "&idCompany=" + surveyAfterUpdate.getCompany().getId();

        final String encryptedLink = EncryptorUtils.encrypt(link);

        surveyAfterUpdate.setLink(encryptedLink);

        return surveyRepository.save(surveyAfterUpdate);
    }

    public String submitAnswer(final Answer answer) throws Exception {

        final List<Survey> listSurvey = surveyRepository.findByLink(answer.getSurvey().getLink());

        this.validSurveyNotFound(listSurvey);

        final Survey survey = listSurvey.get(0);

        this.validStatusSurvey(survey);

        answer.setCustomer(this.findByCustomersByCompanyAndUser(answer.getCustomer(), survey.getCompany()));

        this.validTypeAndCustomer(survey, answer.getCustomer());
        
        answer.setAnswerDate(new Date());
        answer.setSurvey(survey);
        
        answerRepository.save(answer);
        
        return "Your response has been successfully accumulated";
        
    }

    private void validSurveyNotFound(final List<Survey> listSurvey) throws Exception {
        if (listSurvey.size() == 0){
            throw new Exception("Survey not found");
        }
    }

    private void validStatusSurvey(final Survey survey) throws Exception {
        if (!"open".equalsIgnoreCase(survey.getStatus())){
            throw new Exception("Survey is unavailable");
        }
    }

    private void validTypeAndCustomer(final Survey survey, final Customer customer) throws Exception {
        if ("private".equalsIgnoreCase(survey.getType())){
            if (customer == null){
                throw new Exception("Access Denied");
            }
        }
        
    }

    private Customer findByCustomersByCompanyAndUser(final Customer customer, final Company company){
        if (customer != null && customer.getUser() != null){
            final User user = customer.getUser();
                final List<Customer> listCustomers = customerRepository.findByCustomersByUser(company.getId(), 
                                                                                            user.getMail(), 
                                                                                            user.getPassword());
                if (listCustomers.size() == 1){
                    return listCustomers.get(0);
                }
        }
        return null;
    }

}