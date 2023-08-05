package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String brand;
	private String price;
	private String discount;
	private String size;
	private String clothtype;
	@Column(length=1000000)
	private byte[] image;
	
	
	public Dress() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Dress(int id, String name, String brand, String price, String discount, String size, String clothtype,
			byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.discount = discount;
		this.size = size;
		this.clothtype = clothtype;
		this.image = image;
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


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getClothtype() {
		return clothtype;
	}


	public void setClothtype(String clothtype) {
		this.clothtype = clothtype;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}
