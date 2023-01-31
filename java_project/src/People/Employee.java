package People;

import java.io.Serializable;

import Classes.Databases;
import Classes.Message;
import Enums.Gender;

public class Employee extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Employee(String name, String lastName, String password, int age, Gender gender,
			int phoneNumber) {
		super(name, lastName, password, age, gender, phoneNumber);
	}
	//FUNCTION
	public void sendMessage(String messageTo, String messageText) {
		Message a = new Message(this.getLogin(), messageTo, messageText);
		Databases.messages.add(a);
		Databases.save();
    }
    public void getMessage() {
    	Databases.messages.stream().filter(n -> n.getToSomeone().equals(this.getLogin())).forEach(n -> System.out.println(n.getInfoMessage()));
    }
}
