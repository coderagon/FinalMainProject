package com.hitesh.finalmainproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitesh.finalmainproject.models.BankDetails;
import com.hitesh.finalmainproject.service.BranchService;

@RestController
@RequestMapping("/admin/branches")
public class DataController {
    
    @Autowired
    private BranchService service;
    
    
    @GetMapping("/")
    public List<BankDetails> getAll(){
        return service.getAll();
    }
    
}