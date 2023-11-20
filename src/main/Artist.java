package main;

public class Artist {
	private int artistNo;
	private String name;
	private String address;
	private String phNo;
	
	public Artist(int artistNo, String name, String address, String phNo) {
		super();
		this.artistNo = artistNo;
		this.name = name;
		this.address = address;
		this.phNo = phNo;
	}
	
	public int getArtistNo() {
		return artistNo;
	}
	public void setArtistNo(int artistNo) {
		this.artistNo = artistNo;
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
