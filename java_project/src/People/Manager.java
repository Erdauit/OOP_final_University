package People;

import java.io.Serializable;
import java.util.Comparator;
import Classes.Course;
import Classes.Databases;
import Classes.Lesson;
import Classes.NameSorter;
import Classes.News;
import Classes.Request;
import Enums.*;


public class Manager extends Employee implements Serializable, ViewTranscript{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public Manager(String name, String lastName, String password, int age, Gender gender,
		int phoneNumber, ManagerType managerType) {
		super(name, lastName, password, age, gender, phoneNumber);
		this.managerType = managerType;
	}
	//FIELDS
	private ManagerType managerType;

	//GETTERS and SETTERS
	public ManagerType getManagerType() {
		return managerType;
	}
	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	
	//FUNCTION
	public void createCourse(String name, int credits, String description, SemesterType semester, Faculty f) {
		Course a = new Course(name, description, credits, semester, f);
		Databases.courses.add(a);
		Databases.save();
		this.creatingLogFile("The course " + name + " with credits: [" + credits + "] has created succesfully");
		System.out.println("Course has created successfully");
    }
    public void viewRequests() {
    	Databases.requests.stream().filter(n -> n.getType().equals(RequestType.RequestToCourse)).forEach(n -> System.out.println(n.getFrom() + " | " + n.getApprove() + " | "));
    }
    public void approveRegistration(String login, String approve) {
    	for(Request r : Databases.requests) {
    		if(r.getType().equals(RequestType.RequestToCourse)) {
    			if(r.getFrom().equals(login)) {
    				if(approve.equals("APPROVE")) {
    					r.setApprove(true);
    					this.setPermissionToRegistration(login, true);
    					this.addCourseToStudent(login, r.getNameOfRequest());
    					System.out.println("Succesfully approved!");
    					this.creatingLogFile("Request of " + login + " approved");
    					Databases.requests.remove(r);
    					Databases.save();
    					
    				} else if (approve.equals("DENY")) {
    					r.setApprove(false);
    					this.setPermissionToRegistration(login, false);
    					System.out.println("Rejected");
    					this.creatingLogFile("Request of " + login + " rejected");
    					Databases.requests.remove(r);
    					Databases.save();
    				}
    			}
    		}
    	}
    }
    
    public void setPermissionToRegistration(String login, boolean status) {
    	Databases.users.stream().filter(n -> n instanceof Student).map(n -> (Student)n).filter(n -> n.getLogin().equals(login)).forEach(n -> n.setCapableToRegistration(status));
    }
    public void addNews(String title, String message) {
    	News a = new News(title, message);
    	Databases.news.add(a);
    	Databases.save();
    }
    public void addCourseToStudent(String login, String courseName) {
    	for(User u : Databases.users) {
    		if(u instanceof Student) {
    			if(u.getLogin().equals(login)) {
    				((Student) u).registerToCourse(courseName);
    				this.creatingLogFile("The course " + courseName + " has registered succesfully by " + login);
    			}
    		}
    	}
    }
    public void removeNews(String title) {
    	Databases.news.stream().filter(n -> n.getTitle().equals(title)).forEach(n -> Databases.news.remove(n));
    	Databases.save();
    }
    public void orderStudentAlphabetically() {
    	Comparator<User> SortingNames = new NameSorter(); 
    	Databases.users.stream().sorted(SortingNames).filter(n -> n instanceof Student).map(n -> (Student) n).forEach(n -> System.out.println(n.getName() + " " + n.getLastName() + " | " + n.getLogin()));
    }
    
    public void orderStudentByGpa() {
    	Databases.users.stream().filter(n -> n instanceof Student).map(n -> (Student) n).sorted(Comparator.reverseOrder()).forEach(n -> System.out.println(n.getName() + " " + n.getLastName() + " | " + n.getLogin() + " | " +n.getGPA()));
    }
    public void orderTeacherAlphabetically() {
    	Comparator<User> SortingNames = new NameSorter(); 
    	Databases.users.stream().sorted(SortingNames).filter(n -> n instanceof Teacher).map(n -> (Teacher) n).forEach(n -> System.out.println(n.getName() + " " + n.getLastName() + " | " + n.getLogin()));
    }
    
    public void assignTeacherToCourse(String login, String courseName) {
    	Course course = null;
    	Teacher teacher = null;
    	for(Course c : Databases.courses) {
    		if(c.getCourseName().equals(courseName)) {
    			course = c;
    		}
    	}
    	for (User u : Databases.users) {
    		if(u instanceof Teacher) {
    			teacher = (Teacher)u;
    		}
    	}
    	teacher.addCourse(course);
    }
    
    public void assignLessonToStudent(String login, String courseName, String nameOfTeacher, int time, DayOfWeek dayOfWeek, String room, LessonType type) {
    	Lesson a = new Lesson(type, time, dayOfWeek, room, nameOfTeacher, courseName);
    	Databases.users.stream().filter(n -> n instanceof Student).map(n -> (Student)n).filter(n -> n.getLogin().equals(login)).forEach(n -> n.addLesson(a));
    	Databases.save();    	
    	System.out.print("Successfully");

    }
    
    public void viewAllCourses() {
    	Databases.courses.stream().forEach(x -> System.out.println(x.getInfo()));
    }
    
    public void createStatisticalReport() {
    	
    	int cnt = 0;
    	int cntStudentWith3 = 0;
    	int cntStudentWith2 = 0;
    	int cntStudentWith1 = 0;
    	
    	
    	for(User user : Databases.users) {
    		if(user instanceof Student) {
    			cnt++;
    			Student st = (Student) user;
    			if(st.getTranscript().getAvrGpa() > 3) {
    				cntStudentWith3++;
    			}
    			if(st.getTranscript().getAvrGpa() > 2 && st.getTranscript().getAvrGpa() < 3) {
    				cntStudentWith2++;
    			}
    			if(st.getTranscript().getAvrGpa() > 1 && st.getTranscript().getAvrGpa() < 2) {
    				cntStudentWith1++;
    			}
    		}
    	}
    	
    	System.out.println("Overall in university " + cnt + " students");
    	
    	System.out.println("Students with GPA > 3         |" + cntStudentWith3);
    	System.out.println("Students with GPA > 2 but < 3 |" + cntStudentWith2);
    	System.out.println("Students with GPA > 1 but < 2 |" + cntStudentWith1);
    	
    	System.out.println();
    	
    	System.out.println("Average GPA in university is " + this.getAvrGPA());
    	
    	
    }
    
    public Double getAvrGPA() {
    	int cnt = 0;
    	Double sum = 0.0;
    	for(User user: Databases.users) {
    		if(user instanceof Student) {
    			cnt++;
    			Student st = (Student) user;
    			sum = sum + st.getTranscript().getAvrGpa();
    		}
    	}
    	return sum / cnt;
    }
    
	@Override
	public void viewTranscript() {
		
		Databases.users.stream().filter(n -> n instanceof Student).map(n -> (Student) n).forEach(n -> System.out.println(n.getTranscript()));
		
	}
    
    
}
