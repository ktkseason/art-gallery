package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	public boolean checkWelcomeOption(int option) throws Exception {
		if(Pattern.matches("[1-3]", Integer.toString(option)))
			return true;
		else 
			throw new Exception("Option should be from 1 to 3.");
	}
	
	public boolean checkAdminOption(int option) throws Exception {
		if(Pattern.matches("[1-8]", Integer.toString(option)))
			return true;
		else 
			throw new Exception("Option should be from 1 to 8.");
	}
	
	public boolean checkUserOption(int option) throws Exception {
		if(Pattern.matches("[1-7]", Integer.toString(option)))
			return true;
		else 
			throw new Exception("Option should be from 1 to 7.");
	}
	
	public boolean checkArtistAddress(String address) throws Exception {
		if(Pattern.matches("[A-Z][a-z]*, [A-Z][a-z]*", address)) {
			return true;
		} else
			throw new Exception("Address format is incorrect, try again!");
	}
	
	public boolean checkPhNo(String phNo) throws Exception {
		if(Pattern.matches("09-\\d{9}", phNo)) 
			return true;
		else 
			throw new Exception ("Phone number format is incorrect, please try again.");
	}
	
	public boolean checkCustomerAddress(String address) throws Exception {
		if(Pattern.matches("[0-9]+(, [A-Z][a-z]*( [A-Z][a-z]*)*)+", address)) {
			return true;
		} else
			throw new Exception("Address format is incorrect, try again!");
	}
	
	public boolean checkCategory(String category) throws Exception {
		Matcher matcher = Pattern.compile("Potrait|Landscape|Realism|Action|Abstract|Modern").matcher(category);
		if(matcher.find()) 
			return true;
		else 
			throw new Exception("Please choose one of them.");
	}
	
	public boolean checkPrice(String price) throws Exception {
		if(Pattern.matches("[0-9]+\\.[0-9]{2}", price)) 
			return true;
		else 
			throw new Exception ("Price format is invalid.");
	}

}
