package com.hitesh.finalmainproject.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hitesh.finalmainproject.models.BankDetails;
import com.hitesh.finalmainproject.service.BranchService;

@Controller
@RequestMapping("/")
@SessionAttributes("checker")
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
    	map.addAttribute("msg", "Please Enter Valid Branch Details");
    	return "addBranch";
    }
    
    @PostMapping("/contactus/add")
	public String submitBranch(@Valid @ModelAttribute("bank") BankDetails bank,BindingResult result, Model map) {
    	
    	
    	if(result.hasErrors()) {
    		map.addAllAttributes(result.getAllErrors());
    		map.addAttribute("msg","Please Enter valid Branch Details");
    		return "addBranch";
    	}
    	
    	else if(branchService.getById(bank.getId()) != null)
    	{
    		map.addAttribute("msg","Bank Id already Taken");
    		return "addBranch";
    		
    	}
    	
		branchService.save(bank);
		return "redirect:/contactus";
	}
    
    @GetMapping("/contactus/delete/{branch_id}")
    public String deleteEntry(@PathVariable("branch_id") Integer branch_id,Model map)
    {	
    	BankDetails branch = branchService.getById(branch_id);
    	branchService.delete(branch_id);
    	map.addAttribute("msg",branch);
    	return "redirect:/contactus";
    }
    
    @GetMapping("/contactus/edit")
    public String edit(@RequestParam("branch_id") int branch_id, Model map)
    {
    	BankDetails branch =branchService.getById(branch_id);
    	map.addAttribute("branch",branch);
    	map.addAttribute("msg","Please Enter valid Branch Details");
    	return "editPage";
    	
    }
    
    @PostMapping("/contactus/edit")
    public String edit(@Valid @ModelAttribute("branch") BankDetails branch,BindingResult result, Model map)
    {
    	if(result.hasErrors()) {
    		map.addAllAttributes(result.getAllErrors());
    		map.addAttribute("msg","Please Enter valid Branch Details");
    		System.out.println(result.getAllErrors());
    		return "editPage";
    	}
    	
    	
    	branchService.update(branch);
    	map.addAttribute("msg","Edit Operation Successful");
    	return "redirect:/contactus";
    	
    }
    
    @GetMapping("/login")
	public String getTemplate()
	{
		return "loginPage";
	}
    
    @GetMapping("/loginerror")
	public String getFail()
	{
		return "loginFail";
	}
    
    
}