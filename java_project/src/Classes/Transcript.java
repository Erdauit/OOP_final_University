package Classes;

import java.io.Serializable;

import java.util.*;
import java.util.Map.Entry;

public class Transcript implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
    private HashMap<Course, Mark> marks;
    
    
    public Transcript(HashMap<Course, Mark> marks) {
		super();
		this.marks = marks;
	}
    
	//GETTERS and SETTERS

	public HashMap<Course, Mark> getMarks() {
		return marks;
	}
	public void setMarks(HashMap<Course, Mark> marks) {
		this.marks = marks;
	}

    
    //FUNCTIONS
	
	public void getAllGpa() {
		for(Entry<Course, Mark> m: marks.entrySet()) {
			System.out.println(m.getKey().getCourseName() + " " + GpaConverter(m.getValue().sumOfAll()));
		}
	}
	
	public Double getAvrGpa() {
		Double gpa = 0.0;
		for(Entry<Course, Mark> m: marks.entrySet()) {
			gpa += GpaConverter(m.getValue().sumOfAll());
		}
		return gpa;
	}
	
	public double GpaConverter(double mark) {
		if (95<=mark && mark<=100){ return 4.00; }
		if (90<=mark && mark<=94) { return 3.67; }
		if (85<=mark && mark<=89) { return 3.33; }
		if (80<=mark && mark<=84) { return 3.00; }
		if (75<=mark && mark<=79) { return 2.67; }
		if (70<=mark && mark<=74) { return 2.33; }
		if (65<=mark && mark<=69) { return 2.00; }
		if (60<=mark && mark<=64) { return 1.67; }
		if (55<=mark && mark<=59) { return 1.33; }
		if (50<=mark && mark<=54) { return 1.00; }
		return 0.00;
    }
	
	public String GpaConverterToLetter(double mark) {
		if (95<=mark && mark<=100){ return "A "; }
		if (90<=mark && mark<=94) { return "A-"; }
		if (85<=mark && mark<=89) { return "B+"; }
		if (80<=mark && mark<=84) { return "B "; }
		if (75<=mark && mark<=79) { return "B-"; }
		if (70<=mark && mark<=74) { return "C+"; }
		if (65<=mark && mark<=69) { return "C "; }
		if (60<=mark && mark<=64) { return "C-"; }
		if (55<=mark && mark<=59) { return "D+"; }
		if (50<=mark && mark<=54) { return "D "; }
		return "F ";
    }
	
    public void getTranscipt() { 
    	String leftAlignFormat = "| %-30s | %-10s | %-10s | %-10s | %n";
    	System.out.format(leftAlignFormat,"Course","Credits","Overall","Mark");
    	String waka="";
    	for(int i=0;i<73;i++) {
    	    if(i==0 || i==33 || i==46 || i==59 || i==72) waka+="+";
    		else waka+="-";
    	}
    	System.out.println(waka);
    	for(Entry<Course, Mark> m: marks.entrySet()) {
			
    		System.out.format(leftAlignFormat,m.getKey().getCourseName() , m.getKey().getCredits() , m.getValue().sumOfAll() , this.GpaConverterToLetter(m.getValue().sumOfAll()));
    		System.out.println(waka);
    	}
    }
    
    
    
}
