package main;

public class Art {
	private int artNo;
	private String category;
	private String name;
	private String description;
	private float price;
	private String artistName;
	private boolean soldout;
	
	public Art(int artNo, String category, String name, String description, float price, String artistName,
			boolean soldout) {
		super();
		this.artNo = artNo;
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
		this.artistName = artistName;
		this.soldout = soldout;
	}
	
	public int getArtNo() {
		return artNo;
	}
	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public boolean isSoldout() {
		return soldout;
	}
	public void setSoldout(boolean soldout) {
		this.soldout = soldout;
	}
}
