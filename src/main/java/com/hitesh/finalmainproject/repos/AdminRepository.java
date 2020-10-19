package com.hitesh.finalmainproject.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hitesh.finalmainproject.models.AdminDetails;

@Repository
public interface AdminRepository extends CrudRepository<AdminDetails, String>{

}
