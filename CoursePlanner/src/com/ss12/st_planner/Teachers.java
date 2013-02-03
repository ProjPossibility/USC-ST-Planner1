package com.ss12.st_planner;

public class Teachers {
	 //should be extends to multiple teachers
	
	String usernames;
	String passwords;
	
	public Teachers() {
		usernames = new String("st_planner");
		passwords = new String("zaq12wsx");
	}
	
	public String getUsername() {
		return usernames;
	}
	
	public String getPassword() {
		return passwords;
	}

}
