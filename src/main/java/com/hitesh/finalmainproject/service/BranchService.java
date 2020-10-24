package com.hitesh.finalmainproject.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitesh.finalmainproject.models.BankDetails;
import com.hitesh.finalmainproject.repos.BranchRepository;

@Service
public class BranchService {
	
	@Autowired BranchRepository branchdao;
	
	public BankDetails save(BankDetails details)
	{
		return branchdao.save(details);
	}
	
	public BankDetails update(BankDetails details)
	{
		return branchdao.save(details);
	}
	
	public void delete(Integer id)
	{
		branchdao.deleteById(id);
	}
	
	public List<BankDetails> getAll()
	{
		List<BankDetails> detailList = new LinkedList<>();
		branchdao.findAll().forEach(branch->detailList.add(branch));
		return detailList;
	}
	
	public BankDetails getById(Integer id)
	{
		Optional<BankDetails> branch=  branchdao.findById(id);
		if(branch.isPresent())
			return branch.get();
		return null;
	}
}
