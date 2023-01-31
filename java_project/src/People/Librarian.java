package People;

import Classes.*;
import Enums.Gender;
import Enums.RequestType;

public class Librarian extends Employee{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Librarian(String name, String lastName, String password, int age, Gender gender, int phoneNumber) {
		super(name, lastName, password, age, gender, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	
	//FUNCTION
	public void addBook(String name, String author, String desc) {
		Book a = new Book(name, desc, author);
		Databases.books.add(a);
		Databases.save();
    }
    public void seeListOfBooks() {
    	Databases.books.stream().forEach(n -> System.out.println("'" + n.getName() + "'" + " | " + n.getAuthor()));
    }
    public void removeBook(String name) {
    	for(Book b : Databases.books) {
    		if(b.getName().equals(name)) {
    			Databases.books.remove(b);
    		}
    	}
    }
    
    public void giveBook(String login, String nameOfBook) {
    	for(User u : Databases.users) {
    		if(u instanceof Student) {
    			((Student) u).addBook(nameOfBook);
    		}
    	}
    }
    
    public void acceptRequest(String login, String approve) {
    	for(Request r : Databases.requests) {
    		if(r.getType().equals(RequestType.RequestToBook)) {
    			if(r.getFrom().equals(login)) {
    				if(approve.equals("APPROVE")) {
    					r.setApprove(true);
    					this.giveBook(login, r.getNameOfRequest());
    					System.out.println("Succesfully approved!");
    					this.creatingLogFile("Request of " + login + "to take book approved");
    					Databases.requests.remove(r);
    					Databases.save();
    					
    				} else if (approve.equals("DENY")) {
    					r.setApprove(false);
    					System.out.println("Rejected");
    					this.creatingLogFile("Request of " + login + "to take book rejected");
    					Databases.requests.remove(r);
    					Databases.save();
    				}
    			}
    		}
    	}
    }
    public void viewRequest() {
    	Databases.requests.stream().filter(n -> n.getType().equals(RequestType.RequestToBook)).forEach(n -> System.out.println(n.getFrom() + " | " + n.getNameOfRequest() + " | " + n.getApprove() + " | "));
    }
}
