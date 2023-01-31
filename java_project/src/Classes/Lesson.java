package Classes;

import java.io.Serializable;
import java.util.Objects;

import Enums.*;

public class Lesson implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private LessonType type;
    private	int time;
    private DayOfWeek dayOfWeek;
    private String room;
    private String teacherLogin;
    private String courseName;
    private String Id;
    
    
    public Lesson(LessonType type, int time, DayOfWeek dayOfWeek, String room, String teacherName, String courseName) {
		super();
		this.type = type;
		this.time = time;
		this.dayOfWeek = dayOfWeek;
		this.room = room;
		this.setTeacherLogin(teacherName);
		this.courseName = courseName;
		this.Id = this.createId();
	}
    
    
	//GETTERS and SETTERS
	public LessonType getType() {
		return type;
	}
	public void setType(LessonType type) {
		this.type = type;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public DayOfWeek getDayOfWeek() {
		return (DayOfWeek) dayOfWeek;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = (DayOfWeek) dayOfWeek;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTeacherLogin() {
		return teacherLogin;
	}
	public void setTeacherLogin(String teacherLogin) {
		this.teacherLogin = teacherLogin;
	}


	public int getWeek() {
		if(this.dayOfWeek.equals(DayOfWeek.MON)) {
			return 0;
		}
		if(this.dayOfWeek.equals(DayOfWeek.TUE)) {
			return 1;
		}
		if(this.dayOfWeek.equals(DayOfWeek.WED)) {
			return 2;
		}
		if(this.dayOfWeek.equals(DayOfWeek.THU)) {
			return 3;
		}
		if(this.dayOfWeek.equals(DayOfWeek.FRI)) {
			return 4;
		}
		if(this.dayOfWeek.equals(DayOfWeek.SAT)) {
			return 5;
		}
		return 0;
	}
	
	public int getHour() {
		return this.time;
	}
    public String createId() {
    	return this.courseName.toUpperCase().substring(0,1) + Objects.hash(this.room, this.time);
    }
    public String toString() {
    	return "ID: " + this.Id + " | Course :" + this.courseName + " | Room :" +this.room + " | TIME :" + this.time + "\n";
    }


}
