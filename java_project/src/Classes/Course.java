package Classes;

import java.io.Serializable;
import java.util.*;

import Enums.*;

@SuppressWarnings("serial")
public class Course implements Serializable {

	//FIELDS
	private String courseName;
    private String id;
    private String description;
    private int credits;
    private boolean isAvailable;
    private String prerequisite;
    private Vector<Lesson> lessons;
    private Faculty faculty;
    private SemesterType semester;
    
    
    
    
    public Course(String courseName, String id, String description, int credits, SemesterType semester, Faculty faculty) {
		super();
		this.courseName = courseName;
		this.id = id;
		this.description = description;
		this.credits = credits;
		this.semester = semester;
		this.faculty = faculty;
		this.isAvailable = true;
		this.lessons = new Vector<Lesson>();
	}
    public Course(String courseName, String description, int credits, SemesterType semester, Faculty faculty) {
		super();
		this.courseName = courseName;
		this.description = description;
		this.credits = credits;
		this.semester = semester;
		this.faculty = faculty;
		this.id = this.idCreator(courseName);
		this.isAvailable = true;
		this.lessons = new Vector<Lesson>();
		
	}
    
    
	//GETTERS and SETTERS
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getPrerequisite() {
		if(this.prerequisite != null) return prerequisite;
		else return "No prereq"; 
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public Vector<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Vector<Lesson> lessons) {
		this.lessons = lessons;
	}
	public SemesterType getSemester() {
		return semester;
	}
	public void setSemester(SemesterType semester) {
		this.semester = semester;
	}
    public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	
	
	//FUNCTIONS
	public String getInfo() {
		return this.id + " | "+ this.courseName + " | " + this.credits + " | " + this.getPrerequisite() + " | " + this.description;
	}
	public String idCreator(String name) {
		String id = "";
		for(int i=0; i < name.length(); i++) {
			if(name.charAt(i)>= 'A' && name.charAt(i) <= 'Z') {
				id = id + name.charAt(i);
			}
		}
		return id+ "" +Math.abs(Objects.hash(courseName));
	}
    public void addLesson(Lesson a) {
    	this.lessons.add(a);
    	Databases.save();
    	System.out.println("Succesfully");
    }
    
    
}
