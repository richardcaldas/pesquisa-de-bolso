package com.pesquisadebolso.controller;

import java.util.List;

import com.pesquisadebolso.model.Answer;
import com.pesquisadebolso.model.Survey;
import com.pesquisadebolso.service.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    private SurveyService surveyService;

    @GetMapping(value="/find-by-company/{idCompany}")
    public ResponseEntity<List<Survey>> getMethodName(@PathVariable Integer idCompany) {
        return ResponseEntity.ok(surveyService.findByCompany(idCompany));
    }

    @PostMapping(value="/create")
    public ResponseEntity<Survey> create(@RequestBody Survey survey) {
        return ResponseEntity.ok(surveyService.create(survey));
    }


    @PostMapping(value="/submitAnswer")
    public ResponseEntity<String> submitAnswer(@RequestBody Answer answer) {
        try {
            return ResponseEntity.ok(surveyService.submitAnswer(answer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
        
    
}