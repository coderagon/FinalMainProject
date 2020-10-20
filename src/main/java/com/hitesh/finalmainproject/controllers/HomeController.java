package com.hitesh.finalmainproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hitesh.finalmainproject.models.BankDetails;
import com.hitesh.finalmainproject.service.BranchService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired BranchService branchService;

    @PreAuthorize("permitAll")
    @GetMapping
    public String home() {
        return "home";
    }
    
    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }
    
    @GetMapping("/contactus/add")
    public String addBranch(Model map)
    {
    	BankDetails bank = new BankDetails();
    	map.addAttribute("bank",bank);
    	return "addBranch";
    }
    
    @PostMapping("/contactus/add")
	public String submitBranch(@ModelAttribute("bank") BankDetails bank, Model map) {
		branchService.save(bank);
		return "redirect:/contactus";
	}
    
    
    @GetMapping("/login")
	public String getTemplate()
	{
		return "loginPage";
	}
}