package com.hitesh.finalmainproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import com.hitesh.finalmainproject.customvalidator.ContactNumberConstraint;

@Entity
@Table(name="branches")
public class BankDetails {
	
	@Id @Column(name="bank_id")
	@Min(value = 101,message ="*Minimum value should be 101")
	@Max(value = 999,message ="*Mavimum value should be 999")
	private int id;
	
	@Column(name="bank_name")
	@NotBlank(message = "*Bank Name Can't be Blank")
	private String name;
	
	@Column(name="bank_address")
	@NotBlank(message = "*Bank Address can't be Blank")
	private String address;
	
	@Column(name="bank_phone")
	@ContactNumberConstraint
	private String phone_no;
	
	public BankDetails() {
		super();
	}
	
	public BankDetails(int id, String name, String address, String phone_no) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone_no = phone_no;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
}