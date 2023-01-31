package People;

import Classes.Databases;
import Enums.Degree;
import Enums.Employeefactory;
import Enums.Faculty;
import Enums.Gender;
import Enums.ManagerType;
import Enums.ProfessorType;

public class Admin extends Employee{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Admin(String name, String lastName, String password, int age, Gender gender,
			int phoneNumber) {
		super(name, lastName, password, age, gender, phoneNumber);
		
	}
	//FUNCTIONS
	
	public void createStudent(String name, String lastName, String password, int age, Gender gender, int phoneNumber,
			int year, Degree degree, Faculty faculty, String speciality) {
		Student a = new Student(name, lastName, password, age, gender, phoneNumber, year, degree, faculty, speciality);
		Databases.users.add(a);	
		this.creatingLogFile("Student" + name + " " + lastName + " has created succesfully");
		Databases.save();
    }
	public void createTeacher(String name, String lastName, String password, int age, Gender gender, int phoneNumber, ProfessorType professorType) {
		Teacher a = (Teacher) this.createTeacherFactory(name, lastName, password, age, gender, phoneNumber, professorType);
		Databases.users.add(a);	
		
		this.creatingLogFile("Teacher" + name + " " + lastName + " has created succesfully");
		Databases.save();
		
    }
	//FACTORY pattern
	public Employeefactory createTeacherFactory(String name, String lastName, String password, int age, Gender gender, int phoneNumber, ProfessorType professorType) {
		
		return new Teacher(name, lastName, password, age, gender, phoneNumber, professorType);
		
	}
	
	
	public void createManager(String name, String lastName, String password, int age, Gender gender, int phoneNumber, ManagerType managerType) {
		Manager a = new Manager(name, lastName, password, age, gender, phoneNumber, managerType);
		Databases.users.add(a);	
		this.creatingLogFile("Manager" + name + " " + lastName + " has created succesfully");
		Databases.save();
    }
	
	public void createLibrarian(String name, String lastName, String password, int age, Gender gender, int phoneNumber) {
		Librarian a = new Librarian(name, lastName, password, age, gender, phoneNumber);
		Databases.users.add(a);		
		this.creatingLogFile("librarian" + name + " " + lastName + " has created succesfully");
		Databases.save();
	}
	
    public void deleteUser(String name, String lastName) {
    	for(User u :Databases.users) {
    		if(u.getName().equals(name) && u.getLastName().equals(lastName)) {
    			Databases.users.remove(u);
    			this.creatingLogFile("The user of " + name + " " + lastName + "has deleted succesfully");
    		}
    	}
    }
    
    public void changeUsersPassword(String login, String oldPassword, String newPassword) {
    	for(User user: Databases.users) {
    		if(user.getLogin().equals(login)) {
    			user.changePassword(oldPassword, newPassword);
    			this.creatingLogFile("The password of " + login + " has changed succesfully");
    		}
    	}
    }
    
    public void changeStudentsFaculty(String login, Faculty faculty) {
    	for(User user: Databases.users) {
    		if(user.getLogin().equals(login)) {
    			if(user instanceof Student) {
    			Student a = (Student) user;
    			a.changeFaculty(faculty);
    			this.creatingLogFile("The faculty of " + login + " has changed succesfully to " + faculty);
    			}
    		}
    	}
    }
    
    public void viewLogFiles() {
    	Databases.logFiles.stream().forEach(n -> System.out.println(n.getInfo()));
    }
    
    public void viewStudents() {
    	Databases.users.stream().filter(n -> n instanceof Student).map(n -> (Student)n).forEach(n -> System.out.println(n.getBasicInfo()));
    }
    public void viewTeachers() {
    	Databases.users.stream().filter(n -> n instanceof Teacher).map(n -> (Teacher)n).forEach(n -> System.out.println(n.getBasicInfo()));
    }
    public void viewLibrarians() {
    	Databases.users.stream().filter(n -> n instanceof Librarian).map(n -> (Librarian)n).forEach(n -> System.out.println(n.getBasicInfo()));
    }
    public void viewManagers() {
    	Databases.users.stream().filter(n -> n instanceof Manager).map(n -> (Manager)n).forEach(n -> System.out.println(n.getBasicInfo()));
    }
    public void viewAllUsers() {
    	System.out.println("_________Students_________");
    	this.viewStudents();
    	System.out.println("_________Teachers_________");
    	this.viewTeachers();
    	System.out.println("________Librarians_________");
    	this.viewLibrarians();
    	System.out.println("_________Managers_________");
    	this.viewManagers();
    }

   
    
}
