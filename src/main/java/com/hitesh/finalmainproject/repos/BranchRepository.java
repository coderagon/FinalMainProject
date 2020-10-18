package com.hitesh.finalmainproject.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hitesh.finalmainproject.models.BankDetails;

@Repository
public interface BranchRepository extends CrudRepository<BankDetails, Integer>{

}