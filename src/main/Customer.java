package main;

public class Customer {
	private int customerNo;
	private String name;
	private String address;
	private String phNo;
	
	public Customer(int customerNo, String name, String address, String phNo) {
		super();
		this.customerNo = customerNo;
		this.name = name;
		this.address = address;
		this.phNo = phNo;
	}
	
	public int getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
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
	
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
}
