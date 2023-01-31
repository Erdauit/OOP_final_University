package Classes;

import java.io.Serializable;

import java.util.Vector;

public class Schedule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String studentLogin;
	private Vector<Lesson> lessons = new Vector<Lesson>();
	
	public Schedule() {
		
	}

	public Schedule(String studentsName) {
		super();
		this.studentLogin = studentsName;
		
	}

	public String getStudentsName() {
		return studentLogin;
	}
	public void setStudentsName(String studentLogin) {
		this.studentLogin = studentLogin;
	}
	public Vector<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Vector<Lesson> lessons) {
		this.lessons = lessons;
	}
	
	public void addLesson(Lesson a) {
		this.lessons.add(a);
	}
	
	public void viewSchedule() {
		int width = 6;
		int height = 18;
		String [][] k = new String[height][width];
		String [][] cab = new String[height][width];
		int mx=0;
		String spaces = "                                           ";
		for(Lesson l: lessons) {
			int i = l.getTime();
					int j = l.getWeek();
					k[i][j] = l.getCourseName();
					cab[i][j] = l.getRoom()+" room" + " ," + l.getTeacherLogin();
					int len = l.getCourseName().length();
					if(len>mx) mx = len;
		}
		String leftAlignFormat = "| %-5s | %-25s | %-25s | %-25s | %-25s | %-25s | %-25s | %n";
		    System.out.format(leftAlignFormat," " ,"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
		    String waka="";
	    for(int i=0;i<177;i++) {
	    	waka+="-";
	    }
	    
		for(int i=9;i<height;i++) {
			System.out.println(waka);
			String time=""+i;
			if(i<10) time="0"+i;
			System.out.format("| "+ time +":00" + " |");
			for(int j=0;j<width;j++) {
				String ans;
				if(k[i][j]==null) {
					ans= spaces.substring(0,27);
				}
				else {
				ans=k[i][j]+spaces.substring(0,27-k[i][j].length());
				}
				System.out.print(ans+"|");
			}
			System.out.println();
			System.out.format("| "+ "     " + " |");

			for(int j=0;j<width;j++) {
				String ans;
				if(cab[i][j]==null) {
					ans=spaces.substring(0,27);
				}
				else {
				ans=cab[i][j]+spaces.substring(0,27-cab[i][j].length());
				}
				System.out.print(ans+"|");
			}
			
			System.out.println();
		}
		System.out.println(waka);
		
		
		
		
		
		
		

	}
	
	
	
	///FUNCTIONALITY
}
