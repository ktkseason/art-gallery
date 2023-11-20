package main;

import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Admin implements AdminInterface, ArtInterface, ArtistInterface {
	private Scanner sc = new Scanner(System.in);
	private Check check = new Check();
	private static LinkedList<Artist> artistList = new LinkedList<>();
	private static LinkedList<Art> artList = new LinkedList<>();
	private LinkedList<Order> orderList = User.getOrderList();
	
	@Override
	public void adminLogin() {
		System.out.print("Enter Admin Name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter Admin Password: ");
		String pw = sc.nextLine();
		
		if(name.equals("Admin") && pw.equals("admin!@#")) {
			adminWelcome();			
		} else {
			System.out.println("Invalid username and password");
			adminLogin();
		}
	}
	
	@Override
	public void adminWelcome() {
		switch(Welcome.getWel().adminWelcomeOption()) {
			case 1: addArtist(); break;
			case 2: updateArtist(); break;
			case 3: deleteArtist(); break;
			case 4: addArt(); break;
			case 5: deleteArt(); break;
			case 6: viewOrder(); break;
			case 7: viewArtistSale(); break;
			case 8: Welcome.getWel().welcome(); break;
		}
		adminWelcome();
	}
	
	@Override
	public void addArtist() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                               Add New Artist                                                ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		char status;
		do {
			int artNo;
			String name, address = "", phNo = "";
			if(artistList.isEmpty())
				artNo = 1;
			else 
				artNo = artistList.getLast().getArtistNo() + 1;
			
			System.out.print("Artist name: ");
			name = sc.nextLine();
			
			boolean addressStatus = false;
			while(!addressStatus) {
				try {
					System.out.print("Artist address (Town, Township): ");
					address = sc.nextLine();
					
					if(check.checkArtistAddress(address)) {
						addressStatus = true;
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			boolean phStatus = false;
			while(!phStatus) {
				try {
					System.out.print("Artist phone number: ");
					phNo = sc.next();
			
					if(check.checkPhNo(phNo)) {
						phStatus = true;
					}
						
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			artistList.add(new Artist(artNo, name, address, phNo));
			System.out.println("Artist Added Successfully.");
			
			System.out.print("\nAdd another artist? y/n: ");
			status = sc.next().charAt(0);
			sc.nextLine();
		} while(status != 'n');
		
		viewArtist();
	}
	
	@Override
	public void updateArtist() {
		if(!artistList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                                Update Artist                                                ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				viewArtist();
				System.out.print("Artist name: ");
				String name = sc.nextLine();
				
				boolean found = false;
				for(Artist artist: artistList) {
					if(artist.getName().equalsIgnoreCase(name)) {
						System.out.println(artist.getArtistNo() + "\t" + artist.getName() + "\t" + artist.getAddress() + "\t" + artist.getPhNo());
						boolean addressStatus = false;
						while(!addressStatus) {
							try {
								System.out.print("Artist address (Town, Township): ");
								String address = sc.nextLine();
								
								if(check.checkArtistAddress(address)) {
									artist.setAddress(address);
									addressStatus = true;
								}
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						boolean phStatus = false;
						while(!phStatus) {
							try {
								System.out.print("Artist phone number: ");
								String phNo = sc.next();
								
								if(check.checkPhNo(phNo)) {
									artist.setPhNo(phNo);
									phStatus = true;
								}
									
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
						System.out.println("Artist Updated Successfully.");
						found = true;
						break;
					}
				}
				if(!found)
					System.out.println("There is no artist with such name.");
				
				System.out.print("\nUpdate another artist? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else
			System.out.println("There is no artist yet.");
	}
	
	@Override
	public void deleteArtist() {
		if(!artistList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                                Delete Artist                                                ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				viewArtist();
				System.out.print("Artist name: ");
				String name = sc.nextLine();
				
				boolean found = false;
				for(Artist artist: artistList) {
					if(artist.getName().equalsIgnoreCase(name)) {
						System.out.println(artist.getArtistNo() + "\t" + artist.getName() + "\t\t" + artist.getAddress() + "\t" + artist.getPhNo());
						System.out.print("Are you sure you want to delete the artist? y/n: ");
						if(sc.next().charAt(0) == 'y') {
							artistList.remove(artist);
							System.out.println("Artist Deleted Successfully.");
							viewArtist();
						} else 
							System.out.println("Artist wasn't deleted.");
						found = true;
						break;
					} 
				}
				if(!found)
					System.out.println("There is no artist with such name.");
				
				System.out.print("\nDelete another artist? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else
			System.out.println("There is no artist yet.");
	}
	
	@Override
	public void viewArtist() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                              List of Artists                                                ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Artist artist: artistList) {
			System.out.println(artist.getArtistNo() + "\t" + artist.getName() + "\t" + artist.getAddress() + "\t" + artist.getPhNo());
		}
	}
	
	@Override
	public void addArt() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                                Add New Art                                                  ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		char status;
		do {
			int artNo;
			String category = "", name, description, price = "", artistName;
			System.out.print("Artist name: ");
			artistName = sc.nextLine();
			boolean found = false;
			for(Artist artist: artistList) {
				if(artist.getName().equalsIgnoreCase(artistName)) {
					if(artList.isEmpty())
						artNo = 1;
					else 
						artNo = artList.getLast().getArtNo() + 1;
					boolean catStatus = false;
					while(!catStatus) {
						try {
							System.out.print("Choose category (Potrait, Landscape, Realism, Action, Abstract or Modern): ");
							category = sc.next();
							
							if(check.checkCategory(category)) {
								catStatus = true;
							}
						} catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}
					sc.nextLine();
					System.out.print("Art name: ");
					name = sc.nextLine();
					
					System.out.print("Art Description: ");
					description = sc.nextLine();
					
					boolean priceStatus = false;
					while(!priceStatus) {
						try {
							System.out.print("Art price: ");
							price = sc.next();
							
							if(check.checkPrice(price)) {
								priceStatus = true;
							}
								
						} catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}				
					
					artList.add(new Art(artNo, category, name, description, Float.parseFloat(price), artistName, false));
					System.out.println("Art Added Successfully.");
					found = true;
					break;
				}
			}
			if(!found)
				System.out.println("There is no artist with such name. \nPlease add artist first before adding an art.");
			
			System.out.print("\nAdd another art? y/n: ");
			status = sc.next().charAt(0);
			sc.nextLine();
		} while(status != 'n');
		viewArt();
	}

	@Override
	public void deleteArt() {
		if(!artList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                                 Delete Art                                                  ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				viewArt();
				System.out.print("Art name: ");
				String name = sc.nextLine();
				
				boolean found = false;
				for(Art art: artList) {
					if(art.getName().equalsIgnoreCase(name)) {
						String tag;
						if(!art.isSoldout())
							tag = "Sale";
						else
							tag = "Sold Out";
						System.out.println(art.getArtNo() + "\t" + art.getCategory() + "\t" + art.getName() + "\t" + art.getDescription() + "\t" + art.getPrice() + "\t" + art.getArtistName() + "\t" + tag);
						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						boolean orderPresent = false;
						for(Order order: orderList) {
							if(order.getArtName().equalsIgnoreCase(art.getName())) {
								System.out.println(order.getOrderNo() + "\t" + order.getArtName() + "\t" + order.getCustomer().getCustomerNo() + "\t" + order.getCustomer().getName() + "\t" + order.getCustomer().getAddress() + "\t" + order.getCustomer().getPhNo() + "\t" + order.getDate().toString());
								System.out.print("This art is on order. \nDeleting art will also delete the order. \nAre you sure you want to delete this art? y/n: ");
								if(sc.next().charAt(0) == 'y') {
									orderList.remove(order);
									artList.remove(art);
									System.out.println("Art Deleted Successfully.");
								} else
									System.out.println("Art wasn't deleted.");
								orderPresent = true;
								break;
							}
						}
						if(!orderPresent) {
							System.out.print("Are you srue you want to delete this art? y/n: ");
							if(sc.next().charAt(0) == 'y') {
								artList.remove(art);
								System.out.println("Art Deleted Successfully.");
							} else 
								System.out.println("Art wasn't deleted.");
						}
						found = true;
						break;
					}
				}
				if(!found)
					System.out.println("There is no art with such name.");
				
				System.out.print("\nDelete another art? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else
			System.out.println("There is no art yet.");
	}
	
	@Override
	public void viewArt() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                                List of Arts                                                 ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Art art: artList) {
			String tag;
			if(!art.isSoldout())
				tag = "Sale";
			else
				tag = "Sold Out";
			System.out.println(art.getArtNo() + "\t" + art.getCategory() + "\t" + art.getName() + "\t" + art.getDescription() + "\t" + art.getPrice() + "\t" + art.getArtistName() + "\t" + tag);
		}
	}
	
	@Override
	public void viewOrder() {
		if(!orderList.isEmpty()) {
			Comparator<Order> cmp = Comparator.comparing(Order::getDate);
			Collections.sort(orderList, cmp);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                               List of Orders                                                ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(Order order: orderList) {
				System.out.println(order.getOrderNo() + "\t" + order.getArtName() + "\t" + order.getCustomer().getCustomerNo() + "\t" + order.getCustomer().getName() + "\t" + order.getCustomer().getAddress() + "\t" + order.getCustomer().getPhNo() + "\t" + order.getDate().toString());
			}
		} else
			System.out.println("There is no order yet.");
	}

	@Override
	public void viewArtistSale() {
		if(!orderList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                             View Artists' Sales                                             ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				viewArtist();
				System.out.print("Artist name: ");
				String name = sc.nextLine();
				float total = 0;
				boolean found = false;
				for(Order order: orderList) {
					for(Art art: artList) {
						if(art.getName().equalsIgnoreCase(order.getArtName())) {
							for(Artist artist: artistList) {
								if(artist.getName().equalsIgnoreCase(name) && art.getArtistName().equalsIgnoreCase(artist.getName())) {
									total += art.getPrice();
									found = true;
								}
							}
						}
					}
				}
				if(!found)
					System.out.println("There is no artist with such name on sale.");
				else
					System.out.println("Total sales of artist: " + total);
				
				System.out.print("View another artist? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else 
			System.out.println("There is no order yet.");
	}
	
	public static LinkedList<Artist> getArtistList() {
		return artistList;
	}
	
	public static LinkedList<Art> getArtList() {
		return artList;
	}
}
