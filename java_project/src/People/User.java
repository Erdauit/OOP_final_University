package People;
import java.io.Serializable;
import java.util.Objects;

import Classes.Databases;
import Classes.LogFiles;
//import java.lang.*;
import Enums.*;



public class User implements Serializable, Comparable<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private String name ;
	private String lastName;
	private String ID = "";
	private String login;
	private String password;
	private int age;
	private Gender gender;
	private String email;
	private int phoneNumber;
	
	
	public User(String name, String lastName, String password, int age, Gender gender,
			int phoneNumber) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.login = this.createLogin(name, lastName);
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.email = this.createEmail();
		this.phoneNumber = phoneNumber;
		this.ID = this.createID(name, lastName);
	}
	
	
	//GETTERS and SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getID() {
		return ID;
	}
	
	
	
	
	
	//FUNCTIONS
	@Override
	public int hashCode() {
		return Objects.hash(ID, age, email, gender, lastName, login, name, password, phoneNumber);
	}
	
	public String createLogin(String name, String lastName) {
		String login = name.substring(0,1).toLowerCase() + "_" + lastName.toLowerCase();
		for(User u : Databases.users) {
			if(u.getLogin().equals(login)) {
				login = name.substring(0,2).toLowerCase() + "_" + lastName.toLowerCase();
			}
		}
		return login;
	}
	
	public String createEmail() {
		return this.login + "@kbtu.kz";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(ID, other.ID) && age == other.age && Objects.equals(email, other.email)
				&& gender == other.gender && Objects.equals(lastName, other.lastName)
				&& Objects.equals(login, other.login) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && phoneNumber == other.phoneNumber;
	}


	public String createID(String name, String lastName) {
		return this.getAge() + "B" + Math.abs(Objects.hash(name, lastName));
	}
	public void changePassword(String oldPassword, String newPassword) {
		if(oldPassword.equals(this.password)) {
			this.password = newPassword;
		}
	}
	
	public String getBasicInfo() {
		return this.name + " | " + this.lastName + " | " + this.getLogin(); 
	}
    public void viewNews() {
//    	int size = 5;
    	int i = 0;
    	System.out.println("--------------NEWS---------------");
    	while(i != Databases.news.size()) {
    		System.out.println("-------------------------------------");
    		System.out.println(Databases.news.get(i).getNewsInfo());
    		System.out.println("-------------------------------------");
    		i++;
    	}
    }
	public void creatingLogFile(String message) {
		LogFiles a = new LogFiles(this.getName() + " " +this.getLastName(), message);
		Databases.logFiles.add(a);
		Databases.save();
	}
	 public int compareTo(User o) { 
		 
		  // TODO Auto-generated method stub 
		  if(this.age == o.age) return 0; 
		  if(this.age > o.age) return 1; 
		  else return -1; 
		  
	 }


}
