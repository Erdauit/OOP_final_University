package People;

import java.util.*;
import java.util.Map.Entry;

import Classes.*;
import Enums.*;

public class Student extends User implements ViewTranscript{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private int year;
	private Degree degree;
	private Faculty faculty;
	private int creditLimit = 21;
	private String speciality;
	private int totalCredits;
	private boolean capableToRegistration;
    private HashMap<Course, Mark> marks = new HashMap<Course, Mark>();
    private Vector<Book> orderedBook = new Vector<Book>();
	private HashMap <Course, Vector<Boolean>> attendance;
	
	private Transcript transcript = new Transcript(marks);
	private Schedule schedule;

	
	
	public Student(String name, String lastName, String password, int age, Gender gender, int phoneNumber, int year, Degree degree, Faculty faculty, String speciality) {
		super(name, lastName, password, age, gender, phoneNumber);
		this.year = year;
		this.degree = degree;
		this.speciality = speciality;	
		this.faculty = faculty;
		this.marks = new HashMap<Course, Mark>();
		this.transcript = new Transcript(marks);
		this.capableToRegistration = false;
		this.schedule =  new Schedule(this.getLogin());
		this.attendance = new HashMap<Course, Vector<Boolean>>();
	}
	
	
	//GETTERS and SETTERS
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public void addCourse(Course course) {
		Mark a = new Mark();
		this.marks.put(course, a);
		this.attendance.put(course, new Vector<Boolean>());
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public int getTotalCredits() {
		return totalCredits;
	}
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}
	public HashMap<Course, Vector<Boolean>> getAttendance() {
		return attendance;
	}
	public void setAttendance(HashMap<Course, Vector<Boolean>> attendance) {
		this.attendance = attendance;
	}
	public Transcript getTranscript() {
		return transcript;
	}
	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public HashMap<Course, Mark> getMarks() {
		return marks;
	}
	public void setMarks(HashMap<Course, Mark> marks) {
		this.marks = marks;
	}
	public boolean isCapableToRegistration() {
		return capableToRegistration;
	}
	public void setCapableToRegistration(boolean capableToRegistration) {
		this.capableToRegistration = capableToRegistration;
	}	
	public Vector<Book> getOrderedBook() {
		return orderedBook;
	}

	public void setOrderedBook(Vector<Book> orderedBook) {
		this.orderedBook = orderedBook;
	}
	

	//FUNCTIONS
	
	

	

	public void showOrderedBooks() {
		orderedBook.stream().forEach(n -> System.out.println(n.getName() + " | " + n.getAuthor()));
	}
	public void addBook(String name) {
		if(orderedBook == null) {
			orderedBook = new Vector<Book>();
		}
		for(Book b : Databases.books) {
			if(b.getName().equals(name)) {
				orderedBook.add(b);
				Databases.save();
			}
		}
	}
	public void showInfo() {
		System.out.println("-----"+this.getID()+"-----");
		System.out.println("Name : " + this.getName() + "\n" + 
						   "Surname : " + this.getLastName() + "\n" +
						   "Speciality : " + this.getSpeciality() + "\n" +
						   "Faculty : " + this.faculty + "\n");
	}
	
	public void changeFaculty(Faculty newFaculty) {
		this.faculty = newFaculty;
	}
	
	public void setPoint(String courseName, Double point, int chooseTypeOfMarking) {
		
		for(Entry<Course, Mark> m: marks.entrySet()) {
			if(m.getKey().getCourseName().equals(courseName)) {
				if(m.getValue().sumOfFirstAtt() + m.getValue().sumOfSecondAtt() <= 60) {
					if(chooseTypeOfMarking == 1) {
						m.getValue().setFirstAtt(point);
						Databases.save();
					}
					if(chooseTypeOfMarking == 2) {
						m.getValue().setSecondAtt(point);
						Databases.save();
					}
					if(chooseTypeOfMarking == 3) {
						if(point <= 40) {
							m.getValue().setFinalExam(point);
							Databases.save();
						}
					}
				}
			}
		}
	}
	public String getMarkOfCourse(Course course) {	
		for(Entry<Course, Mark> m: marks.entrySet()) {
			if(m.getKey().getCourseName().equals(course.getCourseName())) {
				return this.getName() +" | " + m.getValue().getJournal() ;
			}
		}
		return null;
	}
	
	public void viewMarkOfCourse(Course course) {
		marks.entrySet().stream().filter(n -> n.getKey().getCourseName().equals(course.getCourseName())).forEach(x -> System.out.println(this.getName() +" "+this.getLastName()+" | "+ x.getValue().getJournal()));
	}
	public void registerToCourse(String courseName) {
		for(Course course: Databases.courses) {
			if(course.getCourseName().equals(courseName)) {
				if(course.isAvailable() == false) System.out.println("Course is not available");
				else if(marks.keySet().contains(course)) System.out.println("Course is already registered! （♯▼皿▼）");
				else if(course.getCredits() + totalCredits <= creditLimit) {
					addCourse(course);
					System.out.println("Changes were saved");
					Databases.save();
				}
			}
		}
	}
	public void makeRequest(RequestType request, String message) {
		Request a = new Request(request, this.getLogin(), false, message);
		Databases.requests.add(a);
		Databases.save();
	}
	
	public void dropCourse(String courseName) {
		marks.keySet().removeIf(n -> courseName.equals(n.getCourseName()));
	}
	
	
	
	
	public void viewTranscript() {
		if(transcript == null) {
			this.setTranscript(new Transcript(marks));
		}
		transcript.getTranscipt();
	}
	
	
	public Double getGPA() {
		if(transcript == null) {
			this.setTranscript(new Transcript(marks));
		}
		return transcript.getAvrGpa();
	}
	
	
	public void joinOrganization(String organizationName) {  
		for(Organizations org: Databases.organizations) { 
			if(organizationName.equals(org.getNameOrganization())){ 
				org.addStudent(this); 
				System.out.println("You were added"); 
		   } 
		}  
	}
	
	public void viewSchedule() {
		if(this.schedule == null) {
			this.schedule = new Schedule(this.getLogin());
		}
		this.schedule.viewSchedule();
	}
	
	public void addLesson(Lesson a) {
		
		if(this.schedule == null) {
			this.schedule = new Schedule(this.getLogin());
		}
		this.schedule.addLesson(a);
	}
	
	public void viewLessons() {
		this.schedule.getLessons().stream().forEach(n -> System.out.println(n.getCourseName() + " | " + n.getHour()));
	}
	
	public void viewAttendance(String courseName) {
		if(this.haveYouThisCourse(courseName)) {
			attendance.entrySet().stream().filter(n -> n.getKey().getCourseName().equals(courseName)).forEach(n -> n.getValue());
		}
	}
	
	public void viewFiles(String courseName, String teacherName) {
		Databases.files.stream().filter(n -> n.getCourseName().equals(courseName)).filter(n -> n.getFromWho().equals(teacherName)).forEach(n -> System.out.println(n.getFileName() + "\n   DESCRIPTION: " + n.getDescription()));
	}
	public void viewAttestation() {
		marks.entrySet().stream().forEach(x -> System.out.println(x.getKey().getCourseName() + "   |  " + x.getValue().getAtt()));
	}
	public void viewAvailableCourses() {
		Databases.courses.stream().filter(n -> n.getFaculty().equals(this.faculty)).forEach(x -> System.out.println(x.getInfo()));
	}
	
	public void viewRegisteredCourses() {
		if(marks.isEmpty()) System.out.println("Currently you dont have any courses!");
		marks.entrySet().stream().forEach(x -> System.out.println(x.getKey().getInfo()));		
	}
	
	public void viewOrganizations() {
		Databases.organizations.stream().forEach(n -> System.out.println(n.getNameOrganization()));
	}
	
	public void putRateToTeacher(String login, Double point) {
		Databases.users.stream().filter(n -> n instanceof Teacher).map(n -> (Teacher)n).filter(n -> n.getLogin().equals(login)).forEach(n -> n.setRating(point));
	}
	
	public void viewTeachersRate(String login) {
		Databases.users.stream().filter(n -> n instanceof Teacher).map(n -> (Teacher)n).filter(n -> n.getLogin().equals(login)).forEach(n -> System.out.println(n.getName()+ " " + n.getRating()));
	}
	public void viewListTeachers (String courseName) {
		Databases.users.stream().filter(n -> n instanceof Teacher).map(n -> (Teacher)n).filter(n -> n.haveYouThisCourse(courseName)).forEach(n -> System.out.println(n.getName() + " "+n.getLastName() + " | " + n.getLogin()));
		
	}
	public boolean haveYouThisCourse(String courseName) {
		for(Entry<Course, Mark> m: marks.entrySet()) {
			if(m.getKey().getCourseName().equals(courseName)) {
				return true;
			}
		}
		return false;
	}

	public boolean haveYouThisCourse(Course course) {
		for(Entry<Course, Mark> m: marks.entrySet()) {
			if(m.getKey().getCourseName().equals(course.getCourseName())) {
				return true;
			}
		}
		return false;
	}
	
	public void viewTimeSlot(String courseName, String teacherLogin, String Id) {
		
		Databases.users.stream().filter(n -> n instanceof Teacher).map(n -> (Teacher)n).filter(n -> n.haveYouThisCourse(courseName)).filter(n -> n.getLogin().equals(teacherLogin)).forEach(n -> System.out.println(n.getName() + " "+n.getLastName()+ "\n" + n.getLessons(courseName)));
		
	}
	
	public void chooseTimeSlot(String courseName, String teacherName, String Id) {
		if(this.capableToRegistration) {
			for(User us: Databases.users) {
				if(us instanceof Teacher) {
					this.addLesson(((Teacher) us).getLesson(courseName, Id));
					Databases.save();
				}
			}
		}
		else {
			System.out.println("Registration is closed");
		}
	}
	
	public void setAttendance(String courseName, boolean attend) {
		if(attendance == null) {
			this.attendance = new HashMap<Course, Vector<Boolean>>(); 
		}
		for(Entry<Course, Vector<Boolean>> m: attendance.entrySet()) {
			if(m.getKey().getCourseName().equals(courseName)) {
				m.getValue().add(attend);
				System.out.println("Succesfully");
			}
		}
	}
	
}
