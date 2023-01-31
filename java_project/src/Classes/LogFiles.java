package Classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFiles implements Serializable{
	private static final long serialVersionUID = 1L;
	private String from;
	private String date;
	private String message;
	
	
	public LogFiles(String from, String message) {
		super();
		this.from = from;
		this.date = this.createDate();
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String createDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getInfo () {
		return "[USER : "+ this.from +" ]" + "[DATE : " + this.date + " ]" + "[MESSAGE : "+this.message + " ]"; 
	}
	
}
