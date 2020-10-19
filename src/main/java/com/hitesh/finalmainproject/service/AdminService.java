package com.hitesh.finalmainproject.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitesh.finalmainproject.models.AdminDetails;
import com.hitesh.finalmainproject.repos.AdminRepository;

@Service
public class AdminService {

	@Autowired private AdminRepository admindao;
	
	public AdminDetails getByUserName(String name)
	{
		return admindao.findById(name).get();
	}
	
	public List<AdminDetails> getAll()
	{
		List<AdminDetails> ls = new LinkedList<>();
		admindao.findAll().forEach(admin-> ls.add(admin));
		return ls;
	}

}
