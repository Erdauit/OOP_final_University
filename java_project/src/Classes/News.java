package Classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class News implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public News(String title, String message) {
		this.title = title;
		this.message = message;
		this.date = this.createDate();
	}

	//FIELDS
	private String title;
    private String message;
    private String date;
    
    //GETTERS and SETTERS
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
    //FUNCTION
	public String createDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public String getNewsInfo() {
        return "TITLE :"+ this.title + "\n" + "\n" + "Message: " + this.message + "\n" + "                           " + this.date + "\n";
        
    }
}
