package main;

import java.util.Date;

public class Order {
	private int orderNo;
	private Customer customer;
	private String artName;
	private Date date;
	
	public Order(int orderNo, Customer customer, String artName, Date date) {
		super();
		this.orderNo = orderNo;
		this.customer = customer;
		this.artName = artName;
		this.date = date;
	}
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getArtName() {
		return artName;
	}
	public void setArtName(String artName) {
		this.artName = artName;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
