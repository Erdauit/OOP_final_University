package Classes;

import java.io.Serializable;
import java.util.*;
import People.*;

public class Organizations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private String nameOrganization;
    private Student headOrganization;
    private Vector<Student> members;
    
    public Organizations(String name, Student st) { 
        this.nameOrganization = name; 
        this.headOrganization = st; 
         
    }
    
    //GETTERS and SETTERS
	public String getNameOrganization() {
		return nameOrganization;
	}
	public void setNameOrganization(String nameOrganization) {
		this.nameOrganization = nameOrganization;
	}
	public Student getHeadOrganization() {
		return headOrganization;
	}
	public void setHeadOrganization(Student headOrganization) {
		this.headOrganization = headOrganization;
	}
	public Vector<Student> getMembers() {
		return members;
	}
	public void setMembers(Vector<Student> members) {
		this.members = members;
	}
    
    
    //FUNCTION
	public void addStudent(Student s) {
		members.add(s); 
    }
	 public void removeStudent(Student s) { 
		 if(members.isEmpty() == false) { 
			 if(members.contains(s)) members.remove(s); 
			 	System.out.println(s.getName() + "were removed"); 
		 }	 
		  else System.out.println("You didn't add anyone!"); 
	} 
}
