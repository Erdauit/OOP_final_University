package Classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import People.*;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private String fromSomeone;
    private String toSomeone;
    private String message;
    private String messageDate;
    
    
    
    
    public Message(String fromSomeone, String toSomeone, String message) {
		super();
		this.fromSomeone = fromSomeone;
		this.toSomeone = toSomeone;
		this.message = message;
		this.messageDate = this.createDate();
	}
    
    
	//GETTERS and SETTERS
	public String getFromSomeone() {
		return fromSomeone;
	}
	public void setFromSomeone(String fromSomeone) {
		this.fromSomeone = fromSomeone;
	}
	public String getToSomeone() {
		return toSomeone;
	}
	public void setToSomeone(String toSomeone) {
		this.toSomeone = toSomeone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
    
    
	//FUNCTION
	public String getInfoMessage() {
		String contacts="" + "From: " + this.fromSomeone + " To: " + this.toSomeone;
		String waka="-------------------------------------------";
        return "+"+waka.substring(0,contacts.length()) + "+" + "\n"+"|" + contacts + "|" + "\n" + "+" +waka.substring(0,contacts.length()) + "+" + "\n" + "| MESSAGE: " +"\n" +"| "+ this.message + "\n" + "                       "+this.messageDate;
    }
	public String createDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
