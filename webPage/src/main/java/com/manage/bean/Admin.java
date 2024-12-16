package com.manage.bean;

import java.sql.Blob;

//import java.util.Date;

public class Admin {

	private int id;
	private String email, name, password, company;
	private Blob pic;
	
	public Blob getPic() {
		return pic;
	}

	public void setPic(Blob pic) {
		this.pic = pic;
	}

	public Admin() {
		
	}

	public Admin(String email, String name, String password, String company) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.company = company;
//		this.dob = dob;
	}
//	private Date dob;
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
