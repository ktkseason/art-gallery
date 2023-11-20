package main;

import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

public class User implements UserInterface, OrderInterface {
	private Scanner sc = new Scanner(System.in);
	private Check check = new Check();
	private static LinkedList<Order> orderList = new LinkedList<>();
	private LinkedList<Artist> artistList = Admin.getArtistList();
	private LinkedList<Art> artList = Admin.getArtList();
	
	@Override
	public void userLogin() {
		System.out.print("Enter User Name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter User Password: ");
		String pw = sc.nextLine();
		
		if(name.equals("User") && pw.equals("user!@#")) {
			userWelcome();			
		} else {
			System.out.println("Invalid username and password");
			userLogin();
		}
	}
	
	@Override
	public void userWelcome() {
		switch(Welcome.getWel().userWelcomeOption()) {
			case 1: viewSortedArt(); break;
			case 2: searchByArtist(); break;
			case 3: addOrder(); break;
			case 4: deleteOrder(); break;
			case 5: viewOrder(); break;
			case 6: viewPayment(); break;
			case 7: Welcome.getWel().welcome(); break;
		}
		userWelcome();
	}
	
	@Override
	public void viewSortedArt() {
		if(!artList.isEmpty()) {
			Comparator<Art> cmp = Comparator.comparing(Art::getArtNo);
			Collections.sort(artList, cmp);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                               List of Arts                                                  ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(Art art: artList) {
				String tag;
				if(!art.isSoldout())
					tag = "Sale";
				else
					tag = "Sold Out";
				System.out.println(art.getArtNo() + "\t" + art.getCategory() + "\t" + art.getName() + "\t" + art.getDescription() + "\t" + art.getPrice() + "\t" + art.getArtistName() + "\t" + tag);
			}
		} else 
			System.out.println("No current art.");
	}

	@Override
	public void searchByArtist() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                             Search By Artist                                                ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		char status;
		do {
			System.out.print("Artist name: ");
			String name = sc.nextLine();
			
			boolean found = false;
			for(Artist artist: artistList) {
				if(artist.getName().equalsIgnoreCase(name)) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("                                               List of Arts                                                  ");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					boolean artPresent = false;
					for(Art art: artList) {
						if(art.getArtistName().equals(artist.getName())) {
							String tag;
							if(!art.isSoldout())
								tag = "Sale";
							else
								tag = "Sold Out";
							System.out.println(art.getArtNo() + "\t" + art.getCategory() + "\t" + art.getName() + "\t" + art.getDescription() + "\t" + art.getPrice() + "\t" + art.getArtistName() + "\t" + tag);
							artPresent = true;
						}
					}
					if(!artPresent)
						System.out.println("There is no art of this artist yet.");
					found = true;
					break;
				}
			}
			if(!found)
				System.out.println("There is no artist with such name.");
			
			System.out.print("\nSearch another artist? y/n: ");
			status = sc.next().charAt(0);
			sc.nextLine();
		} while(status != 'n');
	}
	
	@Override
	public void addOrder() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("                                                Add New Order                                                ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		viewSortedArt();
		char status;
		do {
			int orderNo, customerNo;
			String artName, customerName, customerAddress = "", customerPhNo = "";
			
			System.out.print("Art name: ");
			artName = sc.nextLine();
			
			boolean found = false;
			for(Art art: artList) {
				if(art.getName().equalsIgnoreCase(artName)) {
					if(!art.isSoldout()) {
						if(orderList.isEmpty()) {
							orderNo = 1;
							customerNo = 1;
						}
						else {
							orderNo = orderList.getLast().getOrderNo() + 1;
							customerNo = orderList.getLast().getCustomer().getCustomerNo() + 1;
						}
						
						System.out.print("Customer name: ");
						customerName = sc.nextLine();
						
						boolean addressStatus = false;
						while(!addressStatus) {
							try {
								System.out.print("Customer address (No, Street, Town, Township): ");
								customerAddress = sc.nextLine();
								
								if(check.checkCustomerAddress(customerAddress)) {
									addressStatus = true;
								}
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						boolean phStatus = false;
						while(!phStatus) {
							try {
								System.out.print("Customer phone number: ");
								customerPhNo = sc.next();
								
								if(check.checkPhNo(customerPhNo)) {
									phStatus = true;
								}
									
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}						
						orderList.add(new Order(orderNo, new Customer(customerNo, customerName, customerAddress, customerPhNo), artName, new Date()));
						art.setSoldout(true);
						System.out.println("Order Added Successfully.");
						found = true;
						break;
					} else 
						System.out.println("We're sorry. The art is sold out.");
					found = true;
					break;
				}
			}
			if(!found)
				System.out.println("There is no art with such name.");
			
			System.out.print("\nAdd another order? y/n: ");
			status = sc.next().charAt(0);
			sc.nextLine();
		} while(status != 'n');
	}

	@Override
	public void deleteOrder() {
		if(!orderList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                                Delete Order                                                 ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				System.out.print("Customer name: ");
				String customerName = sc.nextLine();
				
				boolean found = false;
				for(Order order: orderList) {
					if(order.getCustomer().getName().equalsIgnoreCase(customerName)) {
						System.out.println(order.getOrderNo() + "\t" + order.getArtName() + "\t" + order.getCustomer().getCustomerNo() + "\t" + order.getCustomer().getName() + "\t" + order.getCustomer().getAddress() + "\t" + order.getCustomer().getPhNo() + "\t" + order.getDate().toString());				
						found = true;
					}
				}
				if(!found)
					System.out.println("There is no order with this customer name");
				else {
					System.out.print("Art Name: ");
					String artName = sc.nextLine();
					boolean isArt = false;
					for(Order order: orderList) {
						if(order.getArtName().equalsIgnoreCase(artName)) {
							if(order.getCustomer().getName().equalsIgnoreCase(customerName)) {
								System.out.println(order.getOrderNo() + "\t" + order.getArtName() + "\t" + order.getCustomer().getCustomerNo() + "\t" + order.getCustomer().getName() + "\t" + order.getCustomer().getAddress() + "\t" + order.getCustomer().getPhNo() + "\t" + order.getDate().toString());				
								System.out.print("Are you sure you want to delete this order? y/n: ");
								if(sc.next().charAt(0) == 'y') {
									for(Art art: artList) {
										if(art.getName().equalsIgnoreCase(artName)) {
											art.setSoldout(false);
											break;
										}
									}
									orderList.remove(order);
									System.out.println("Order Deleted Successfully.");
								} else 
									System.out.println("Order wan't deleted.");	
							} else 
								System.out.println("You only can delete your order.");
							isArt = true;
							break;
						}
					}
					if(!isArt) 
						System.out.println("There is no art with such name.");
				}
				
				System.out.print("\nDelete another order? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else
			System.out.println("There is no order yet.");
	}
	
	@Override
	public void viewOrder() {
		if(!orderList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                                 View Order                                                  ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				System.out.print("Customer name: ");
				String customerName = sc.nextLine();
				
				boolean found = false;
				for(Order order: orderList) {
					if(order.getCustomer().getName().equalsIgnoreCase(customerName)) {
						System.out.println(order.getOrderNo() + "\t" + order.getArtName() + "\t" + order.getCustomer().getCustomerNo() + "\t" + order.getCustomer().getName() + "\t" + order.getCustomer().getAddress() + "\t" + order.getCustomer().getPhNo() + "\t" + order.getDate().toString());
						found = true;
					}
				}
				if(!found) 
					System.out.println("There is no customer with such name.");
				
				System.out.print("View another? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else 
			System.out.println("There is no order yet.");
	}
	
	@Override
	public void viewPayment() {
		if(!orderList.isEmpty()) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("                                               View Payment                                                  ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			char status;
			do {
				System.out.print("Customer name: ");
				String customerName = sc.nextLine();
				float total = 0;
				
				boolean found = false;
				for(Order order: orderList) {
					if(order.getCustomer().getName().equalsIgnoreCase(customerName)) {
						for(Art art: artList) {
							if(art.getName().equalsIgnoreCase(order.getArtName())) {
								total += art.getPrice();
								found = true;
							}
						}						
					}
				}
				if(!found) 
					System.out.println("There is no order with this customer name.");
				else 
					System.out.println("Total payment: " + total);
				
				System.out.print("View another payment? y/n: ");
				status = sc.next().charAt(0);
				sc.nextLine();
			} while(status != 'n');
		} else
			System.out.println("There is no order yet.");
	}
	
	public static LinkedList<Order> getOrderList() {
		return orderList;
	}
}

