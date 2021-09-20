package com.webapp.demo.studentEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name = "Name", nullable = false)
	@NotEmpty(message = "Name cannot be empty!")
	@Size(min = 2, message = "Enter a valid, at least 2 character name!" )
	private String name;
	
	@Column(name = "Surname", nullable = false)
	@NotEmpty(message = "Surname cannot be empty")
	@Size(min = 2, message = "Enter a valid, at least 2 character surname!" )
	private String surname;
	
	@Column(name = "Phone_Number")
	@NotEmpty(message = "Phone number cannot be empty")
	private String phoneNumber;
	
	@Column(name = "City")
	@NotEmpty(message = "City cannot be empty")
	@Size(min = 2, message ="Enter a valid city name" )
	private String city;
	
	@Column(name = "District")
	@NotEmpty(message = "District cannot be empty")
	@Size(min = 2, message ="Enter a valid district name" )
	private String district;
	
	@Column(name = "Description")
	@NotEmpty(message = "Description fied must be filled")
	private String description;

	
	private String photo;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Student() {
	
	}
	
	@JsonCreator
	public Student(@JsonProperty("id") Integer id,
			@JsonProperty ("name") String name,
			@JsonProperty ("surname") String surname,
			@JsonProperty ("phoneNumber") String phoneNumber,
			@JsonProperty ("city") String city,
			@JsonProperty ("district") String district,
			@JsonProperty ("description") String description
			) {
		this.id=id;
		this.city=city;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.surname = surname;
		this.district=district;
		this.description=description;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber
				+ ", city=" + city + ", district=" + district + ", description=" + description + "]";
	}
	

}

