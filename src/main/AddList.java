package main;

import java.util.LinkedList;
import java.util.Date;
import java.util.Scanner;

public class AddList {
	Scanner sc = new Scanner(System.in);
	Check check = new Check();
	
	private LinkedList<Artist> artistList = Admin.getArtistList();
	private LinkedList<Art> artList = Admin.getArtList();
	private LinkedList<Order> orderList = User.getOrderList();
	
	AddList() {		
		artistList.add(new Artist(1, "Vincent", "Paris, France", "09-123432213"));
		artistList.add(new Artist(2, "Monet", "Havre, France", "09-345434567"));
		artistList.add(new Artist(3, "Dali", "Figueres, Spain", "09-456543454"));
		artistList.add(new Artist(4, "Davinci", "Vinci, Italy", "09-457685767"));
		artistList.add(new Artist(5, "Francis", "London, Britain", "09-456768769"));
		
		artList.add(new Art(1, "Landscape", "Starry Night", "A beautiful motionful night.", 345.67f, "Vincent", false));
		artList.add(new Art(2, "Potrait", "Vincent", "Own image of vincent.", 854.05f, "Vincent", false));
		artList.add(new Art(3, "Landscape", "Almond Bloosom", "Almond tree full of flowers.", 637.34f, "Vincent", true));
		artList.add(new Art(4, "Landscape", "Lotus Pond", "Steady pond of lotuss", 437.45f, "Monet", false));
		artList.add(new Art(5, "Action", "Eagle", "The great mighty bird.", 236.45f, "Monet", false));
		artList.add(new Art(6, "Realism", "Pacnic", "Family fun fair.", 87.24f, "Monet", false));
		artList.add(new Art(7, "Abstract", "Watch", "Melting time.", 742.39f, "Dali", true));
		artList.add(new Art(8, "Modern", "The man", "Life of work, life of living.", 623.36f, "Dali", false));
		artList.add(new Art(9, "Potrait", "David", "Man feature.", 3452.33f, "Davinci", true));
		artList.add(new Art(10, "Potriat", "Mona Lisa", "She won't smile.", 724.56f, "Davinci", false));
		
		orderList.add(new Order(1, new Customer(1, "Bo Bo", "23, Hlaing, Myeik", "09-234876345"), "Almond Bloosom", new Date()));
		orderList.add(new Order(2, new Customer(2, "Do Do", "57, Pyay, Nyaung", "09-984783987"), "Watch", new Date()));
		orderList.add(new Order(3, new Customer(3, "Fo Fo", "84, Monn, Thaung", "09-324980587"), "David", new Date()));
	}
}
