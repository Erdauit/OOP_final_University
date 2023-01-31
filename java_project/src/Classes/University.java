package Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Enums.DayOfWeek;
import Enums.Degree;
import Enums.Faculty;
import Enums.Gender;
import Enums.LessonType;
import Enums.ManagerType;
import Enums.ProfessorType;
import Enums.RequestType;
import Enums.SemesterType;
import People.Admin;
import People.Librarian;
import People.Manager;
import People.Student;
import People.Teacher;
import People.User;

public class University {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		Databases.load();
		
		System.out.println("Please, enter login");
		String input = reader.readLine();
		
		for(User user: Databases.users) {
			if(user.getLogin().equals(input)) {
				System.out.println("Please, enter password");
				input = reader.readLine();
				if(user.getPassword().equals(input)) {
					System.out.println("Welcome, dear "+ user.getName()+ " "+ user.getLastName());

					user.viewNews();
					System.out.println("\n");
					System.out.println("-------Enter YES to return to main menu-------");					
					input = reader.readLine();
					Boolean flag = false;

					if(input.equals("YES") || input.equals("yes") || input.equals("Yes")) {
						flag = true;
					}
					
					
	//CONSOLE FOR STUDENT			
					
					
					if(user instanceof Student) {
						while(flag) {
							Student st = (Student) user; 
							System.out.println("-----Kazakh-British Technical University-----");
							System.out.println("-----------------Student mode----------------\n");
							System.out.println("Please, choose on of the functions:\n"+ "\n"+
											   "0    : Logging out\n" +
											   "1    : View info about yourself\n" +
											   "2    : Register to course\n" +
											   "3    : Drop course\n" +
											   "4    : View available courses\n" +
											   "5    : View registered courses\n" +
											   "6    : Select time slot for lessons\n" +
											   "7    : View attestation\n"+
											   "8    : View transcript\n" +
											   "9    : View organizations\n"+
											   "10   : Join to organization\n" +
											   "11   : Make request\n" +
											   "12   : View Schedule\n"+
											   "13   : Put rate to teacher\n"+
											   "14   : View teacher's rate\n"+
											   "15   : View list of books\n"+
											   "16   : View files of coure\n"+
											   "17   : View lessons");
							
							if(st.getDegree().equals(Degree.Master) || st.getDegree().equals(Degree.PHD)) {
								System.out.println("18   : Publish article\n" +
												   "19   : Write project\n");
							}
							input = reader.readLine();
							if(input.equals("0")) {
								Databases.save();
								flag = false;
							}
							if(input.equals("1")) {
								st.showInfo();
							}
							if(input.equals("2")) {
								if(st.isCapableToRegistration() == true) {
									System.out.println("Write down the name of course");
									input = reader.readLine();
									st.registerToCourse(input);
								}
								else {
									System.out.println("You have no permission");
								}
							}
							if(input.equals("3")) {
								System.out.println("Write down the name of course");
								input = reader.readLine();
								st.dropCourse(input);
							}
							if(input.equals("4")) {
								st.viewAvailableCourses();
								System.out.println("\n");
							}
							if(input.equals("5")) {
								st.viewRegisteredCourses();
								System.out.println("\n");
							}
							if(input.equals("6")) {
								System.out.println("Write the name of course");
								String courseName = reader.readLine();
								st.viewListTeachers(courseName);
								System.out.println("-------------------------");
								System.out.println("Write the login of teacher");
								String teacherLogin = reader.readLine();
								st.viewTimeSlot(courseName, teacherLogin, input);
								
								System.out.println("Write the ID of lesson");
								String id = reader.readLine();
								
								st.chooseTimeSlot(courseName, teacherLogin, id);
								
							}
							if(input.equals("7")) {
								st.viewAttestation();
								System.out.println("\n");
							}
							if(input.equals("8")) {
								st.viewTranscript();
								System.out.println("\n");
							}
							if(input.equals("9")) {
								st.viewOrganizations();
								System.out.println("\n");
							}
							if(input.equals("10")) {
								System.out.println("Write down the name of organization"); 
						        String orgName = reader.readLine(); 
						        st.joinOrganization(orgName);
							}
							if(input.equals("11")) {
								System.out.println("Enter the type of request: \n1. For registration\n2. For order book");
								String choosen = reader.readLine();
								if(choosen.equals("1")) {
									System.out.println("Write down the name of course that you want to register"); 
							        String courseName = reader.readLine(); 
									st.makeRequest(RequestType.RequestToCourse, courseName);
								}
								if(choosen.equals("2")) {
									System.out.println("Write down the name of book that you want to take"); 
							        String bookName = reader.readLine(); 
									st.makeRequest(RequestType.RequestToBook, bookName);
								}
							}
							if(input.equals("12")) {
								st.viewSchedule();
							}
							if(input.equals("13")) {
								System.out.println("Enter the login of the teacher");
								String login = reader.readLine();
								System.out.println("Enter the rate [0 - 5]");
								Double point = Double.parseDouble(reader.readLine());
								st.putRateToTeacher(login, point);
								System.out.println("Thank you for feedback!");
							}
							if(input.equals("14")) {
								System.out.println("Enter login of teacher");
								String login = reader.readLine();
								st.viewTeachersRate(login);
							}
							if(input.equals("15")) {
								st.showOrderedBooks();
							}
							if(input.equals("16")) {
								System.out.println("Enter name of course");
									
							}
							if(input.equals("17")) {
								st.viewLessons();
							}
							if(input.equals("18")) {
								
							}
							if(input.equals("19")) {
								
							}
						}
					}
					
	///CONSOLE FOR ADMIN		
					
					if(user instanceof Admin) {
						while(flag) {
							Admin admin = (Admin) user; 
							System.out.println("-----Kazakh-British Technical University-----");
							System.out.println("---------------Admin's console----------------");
							System.out.println("Please, choose on of the functions:\n"+
											   "1  : Update information about user\n" +
											   "2  : See log files\n" +
											   "3  : Create student\n" +
											   "4  : Create teacher\n" +
											   "5  : Create manager\n" +
											   "6  : Create librarian\n" +
											   "7  : View students\n" +
											   "8  : View teachers\n"+
											   "9  : View librarians\n"+
											   "10 : View Managers\n"+
											   "11 : View ALL users\n"+
											   "12 : Send Message\n" +
											   "13 : View Message\n" +
											   "14 : Delete user\n" +
											   "0  : Logging Out\n");
							input = reader.readLine();
							if(input.equals("0")) {
								Databases.save();
								flag = false;
							}
							if(input.equals("1")) {
					    		System.out.println("Please write login of user"); 
					    		String login = reader.readLine();
					    		System.out.println("What you want to change?\n"+
					    						   "1 : Password\n"+
					    						   "2 : Faculty\n");
					    		input = reader.readLine();
					    		if(input.equals("1")) {
					    			System.out.println("Write old password for user: \n");
					    			String oldPassword = reader.readLine();
					    			System.out.println("Write new password for user: \n");
					    			String newPassword = reader.readLine();
					    			admin.changeUsersPassword(login, oldPassword, newPassword);
					    		}
					    		if(input.equals("2")) {
					    			System.out.println("Write new Faculty for user: \n");
					    			System.out.println("List of Faculties, choose one: \n" +
					    							   "1. FIT\n2. FGIG\n3. FANGI\n4. BS\n5. ISE\n6. KMA\n7. NOCHI\n");
					    			input = reader.readLine();
					    			if(input.equals("1")) {
					    				admin.changeStudentsFaculty(login, Faculty.FIT);
					    			}
					    			else if(input.equals("2")) {
					    				admin.changeStudentsFaculty(login, Faculty.FGIG);
					    			}
					    			else if(input.equals("3")) {
					    				admin.changeStudentsFaculty(login, Faculty.FANGI);
					    			}
					    			else if(input.equals("4")) {
					    				admin.changeStudentsFaculty(login, Faculty.BS);
					    			}
					    			else if(input.equals("5")) {
					    				admin.changeStudentsFaculty(login, Faculty.ISE);
					    			}
					    			else if(input.equals("6")) {
					    				admin.changeStudentsFaculty(login, Faculty.KMA);
					    			}
					    			else if(input.equals("7")) {
					    				admin.changeStudentsFaculty(login, Faculty.NOCHI);
					    			}
					    		}
							}	
							if(input.equals("2")) {
								admin.viewLogFiles();
							}
							if(input.equals("3")) {
					    		System.out.println("Please, enter name");
					    		String name = reader.readLine();
					    		System.out.println("Please, enter  last name");
					    		String lastName = reader.readLine();
					    		System.out.println("Please, enter password");
					    		String password = reader.readLine();
					    		System.out.println("Please, enter age");
					    		int age = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose gender :\n" + "1. MALE\n2. FEMALE");
					    		input = reader.readLine();
					    		Gender g = null;
				    			if(input.equals("1")) {
				    				g = Gender.MALE;
				    			}
				    			if(input.equals("2")) {
				    				g = Gender.FEMALE;
				    			}
					    		System.out.println("Please, enter phoneNummber");
					    		int phone = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, enter year of study");
					    		int year = Integer.parseInt(reader.readLine());
					    		Degree degree = null;
					    		System.out.println("Please, choose degree :\n" + "1. BACHELOR\n2. MASTER\n3. PHD");
					    		input = reader.readLine();
				    			if(input.equals("1")) {
				    				degree = Degree.Bachelor;
				    			}
				    			if(input.equals("2")) {
				    				degree = Degree.Master;
				    			}
				    			if(input.equals("3")) {
				    				degree = Degree.PHD;
				    			}
				    			System.out.println("List of Faculties, choose one: \n" +
		    							   "1. FIT\n2. FGIG\n3. FANGI\n4. BS\n5. ISE\n6. KMA\n7. NOCHI\n");
				    			Faculty f = null;
				    			input = reader.readLine();
				    			if(input.equals("1")) {
				    				f = Faculty.FIT;
				    			}
				    			if(input.equals("2")) {
				    				f = Faculty.FGIG;
				    			}
				    			if(input.equals("3")) {
				    				f = Faculty.FANGI;
				    			}
				    			if(input.equals("4")) {
				    				f = Faculty.BS;
				    			}
				    			if(input.equals("5")) {
				    				f = Faculty.ISE;
				    			}
				    			if(input.equals("6")) {
				    				f = Faculty.KMA;
				    			}
				    			if(input.equals("7")) {
				    				f = Faculty.NOCHI;
				    			}
				    			System.out.println("Please, enter speciality");
				    			String spec = reader.readLine();
				    			admin.createStudent(name, lastName, password, age, g, phone, year, degree, f, spec);
					    	}
							if(input.equals("4")) {
								System.out.println("Please, enter name");
					    		String name = reader.readLine();
					    		System.out.println("Please, enter  last name");
					    		String lastName = reader.readLine();
					    		System.out.println("Please, enter password");
					    		String password = reader.readLine();
					    		System.out.println("Please, enter age");
					    		int age = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose gender :\n" + "1. MALE\n2. FEMALE");
					    		input = reader.readLine();
					    		Gender g = null;
				    			if(input.equals("1")) {
				    				g = Gender.MALE;
				    			}
				    			if(input.equals("2")) {
				    				g = Gender.FEMALE;
				    			}
					    		System.out.println("Please, enter phoneNummber");
					    		int phone = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose Teacher's type :\n" + "1. Practice tutor\n2. Professor\n3. Senior Lecturer\n4. Tutor");
					    		input = reader.readLine();
					    		ProfessorType t = null;
				    			if(input.equals("1")) {
				    				t = ProfessorType.PracticeTutor;
				    			}
				    			if(input.equals("2")) {
				    				t = ProfessorType.Professor;
				    			}
				    			if(input.equals("3")) {
				    				t = ProfessorType.SeniorLectures;
				    			}
				    			if(input.equals("4")) {
				    				t = ProfessorType.Tutors;
				    			}
				    			admin.createTeacher(name, lastName, password, age, g, phone, t);
					    	}
							if(input.equals("5")) {
								System.out.println("Please, enter name");
					    		String name = reader.readLine();
					    		System.out.println("Please, enter  last name");
					    		String lastName = reader.readLine();
					    		System.out.println("Please, enter password");
					    		String password = reader.readLine();
					    		System.out.println("Please, enter age");
					    		int age = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose gender :\n" + "1. MALE\n2. FEMALE");
					    		input = reader.readLine();
					    		Gender g = null;
				    			if(input.equals("1")) {
				    				g = Gender.MALE;
				    			}
				    			if(input.equals("2")) {
				    				g = Gender.FEMALE;
				    			}
					    		System.out.println("Please, enter phone Number");
					    		int phone = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose the manager type :\n" + "1. OR\n2. Departments");
					    		input = reader.readLine();
					    		ManagerType m = null;
				    			if(input.equals("1")) {
				    				m = ManagerType.OR;
				    			}
				    			if(input.equals("2")) {
				    				m = ManagerType.Departments;
				    			}
				    			admin.createManager(name, lastName, password, age, g, phone, m);
					    	}
							if(input.equals("6")) {
								System.out.println("Please, enter name");
					    		String name = reader.readLine();
					    		System.out.println("Please, enter  last name");
					    		String lastName = reader.readLine();
					    		System.out.println("Please, enter password");
					    		String password = reader.readLine();
					    		System.out.println("Please, enter age");
					    		int age = Integer.parseInt(reader.readLine());
					    		System.out.println("Please, choose gender :\n" + "1. MALE\n2. FEMALE");
					    		input = reader.readLine();
					    		Gender g = null;
				    			if(input.equals("1")) {
				    				g = Gender.MALE;
				    			}
				    			if(input.equals("2")) {
				    				g = Gender.FEMALE;
				    			}
				    			System.out.println("Please, enter phone Number");
					    		int phone = Integer.parseInt(reader.readLine());
					    		admin.createLibrarian(name, lastName, password, age, g, phone);
					    	}
							if(input.equals("7")) {
					    		admin.viewStudents();
					    	}
							if(input.equals("8")) {
					    		admin.viewTeachers();
					    	}
							if(input.equals("9")) {
					    		admin.viewLibrarians();
					    	}
							if(input.equals("10")) {
					    		admin.viewManagers();
					    	}
							if(input.equals("11")) {
					    		admin.viewAllUsers();
					    	}
							if(input.equals("12")) {
								System.out.println("Write the login of employee you want to message to:");
					    		String login = reader.readLine();
					    		System.out.println("Write the message"); 
					    		String message = reader.readLine();
					    		admin.sendMessage(login, message);
					    	}
							if(input.equals("13")) {
					    		admin.getMessage();
					    	}
							if(input.equals("14")) {
								System.out.println("Write the name of user that you want to delete");
					    		String name = reader.readLine();
					    		System.out.println("Write the last name of user that you want to delete"); 
					    		String lastName = reader.readLine();
					    		admin.deleteUser(name, lastName);
					    	}
						}
					}
					
					
	///CONSOLE FOR TEACHER						
					
					if(user instanceof Teacher) {
						while(flag) {
							Teacher teacher = (Teacher)user;
							System.out.println("-----Kazakh-British Technical University-----");
							System.out.println("--------------Teacher's console--------------");
							System.out.println("Please, choose on of the functions:\n"+
											   "1  : Put marks\n" +
											   "2  : View List of Students\n" +
											   "3  : Add Files\n" +
											   "4  : View Files\n" +
											   "5  : View your courses\n" + 
											   "6  : Send Message\n" +
											   "7  : View Message\n" +
											   "8  : See rating\n" +
											   "9  : Put attendance\n" +
											   "10 : Put time slot for lesson\n" +
											   "0 : Logging out");
							
							input = reader.readLine();
							if(input.equals("0")) {
								Databases.save();
								flag = false;
							}
							if(input.equals("1")) {
								System.out.println("Write course that your reaches");
								String course = reader.readLine();
								if(teacher.haveYouThisCourse(course)) {
									System.out.println("Write login of Student");
									String loginOfStudent = reader.readLine();
									System.out.println("Choose the way of marking\n" +
													   "1 : First attestation\n" +
													   "2 : Second attestation\n" +
													   "3 : Final exam\n");
									int chooseTypeOfMarking =Integer.parseInt(reader.readLine());
									System.out.println("Enter point : ");
									Double point =Double.parseDouble(reader.readLine());
									teacher.setPointToStudent(loginOfStudent, course, point, chooseTypeOfMarking);
									System.out.println("---------------Succesfully!---------------");
								} else {
									System.out.println("It is not your course!");
								}
							}
							if(input.equals("2")) {
								System.out.println("Write course that your reaches");
								String course = reader.readLine();							
								teacher.viewStudents(course);
								
							}
							if(input.equals("3")) {
								System.out.println("Write a course name:");
								input = reader.readLine();
								for(Course course : Databases.courses) {
									if(course.getCourseName().equals(input)) {
										System.out.println("Write a file's name");
										String fileName = reader.readLine();
										System.out.println("Write a file's description");
										String fileDesc = reader.readLine();
										teacher.addFile(course, new File(course.getCourseName(), fileName, fileDesc, teacher.getLogin()));
									}
								}
							}
							if(input.equals("4")) {
								System.out.println("Write a course name:");
								String courseName = reader.readLine();
								teacher.viewFiles(courseName);
							}
							if(input.equals("5")) {
								teacher.viewMyCourses();
							}
							if(input.equals("6")) {
								System.out.println("Write the login of employee you want to message to:");
					    		String login = reader.readLine();
					    		System.out.println("Write the message"); 
					    		String message = reader.readLine();
					    		teacher.sendMessage(login, message);
							}
							if(input.equals("7")) {
								teacher.getMessage();
							}
							if(input.equals("8")) {
								System.out.println(teacher.getRating());
							}
							if(input.equals("9")) {
								System.out.println("Write the course name");
								String courseName = reader.readLine();
								System.out.println("Write the student login");
								String studentLogin = reader.readLine();
								System.out.println("Choose the attend: \n1. Participated\n2. Don't participated");
								input = reader.readLine();
								boolean attend = false;
								if(input.equals("1")) {
									attend = true;
								}
								if(input.equals("2")) {
									attend = false;
								}
								teacher.putAttendance(courseName, studentLogin, attend);
							}
							if(input.equals("10")) {
								System.out.println("Write course of lesson");
								String courseName = reader.readLine();
								System.out.println("Write the time of lesson in format {18, 9}");
								int time  = Integer.parseInt(reader.readLine());
								System.out.println("Choose the format of lessons :\n" + "1. PRACTISE\n2. LECTURE\n3. LAB WORK");
								input = reader.readLine();
								LessonType type = null;
								if(input.equals("1")) {
									type = LessonType.Practise;
								}
								if(input.equals("2")) {
									type = LessonType.Lecture;
								}
								if(input.equals("3")) {
									type = LessonType.laboratory;
								}
								
					    		System.out.println("Please, choose day of week :\n" + "1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday\n6. Saturday");
					    		input = reader.readLine();
					    		DayOfWeek day = null;
				    			if(input.equals("1")) {
				    				day = DayOfWeek.MON;
				    			}
				    			if(input.equals("2")) {
				    				day = DayOfWeek.TUE;
				    			}
				    			if(input.equals("3")) {
				    				day = DayOfWeek.WED;
				    			}
				    			if(input.equals("4")) {
				    				day = DayOfWeek.THU;
				    			}
				    			if(input.equals("5")) {
				    				day = DayOfWeek.FRI;
				    			}
				    			if(input.equals("6")) {
				    				day = DayOfWeek.SAT;
				    			}
				    			System.out.print("Enter the room");
				    			String room = reader.readLine();
								teacher.createLesson(courseName, room, day, time, type);
							}
						}
					}
					
					
					
					
					if(user instanceof Manager) {
						while(flag) {
							Manager manager = (Manager)user;
							System.out.println("-----Kazakh-British Technical University-----");
							System.out.println("--------------Manager's console--------------");
							System.out.println("Please, choose on of the functions:\n"+
											   "1  : Create course\n" +
											   "2  : Assign teacher to a course\n" +
											   "3  : Create statistical report\n" +
											   "4  : Manage news\n" +
											   "5  : View all courses\n" + 
											   "6  : Send Message\n" +
											   "7  : View Message\n" +
											   "8  : Manage request to registration\n" +
											   "9  : View requests\n"+
											   "10 : View news\n"+
											   "11 : Sort students alphabetically\n" +
											   "12 : Sort student by GPA\n"+
											   "13 : Sort teachers alphabetically\n" +
											   "14 : Add lesson to student\n" +
											   "0  : Logging out");
							input = reader.readLine();
							
							if(input.equals("0")) {
								Databases.save();
								flag = false;
							}
							
							if(input.equals("1")) {
								System.out.println("Write course's name");
								String courseName = reader.readLine();
								System.out.println("Write course's description");
								String desc = reader.readLine();
								System.out.println("Write course's credits");
								int credits =Integer.parseInt(reader.readLine());
								System.out.println("Choose semester type\n" + "1. FALL\n" + "2. SPRING\n");
								String semester = reader.readLine();
								SemesterType semac = null;
				    			if(semester.equals("1")) {
				    				semac = SemesterType.FALL;
				    			} 
				    			else if(semester.equals("2")) {
				    				semac = SemesterType.SPRING;
				    			}
				    			System.out.println("List of Faculties, choose one:\n" +
		    							   "1. FIT\n2. FGIG\n3. FANGI\n4. BS\n5. ISE\n6. KMA\n7. NOCHI\n");
				    			Faculty f = null;
				    			input = reader.readLine();
				    			if(input.equals("1")) {
				    				f = Faculty.FIT;
				    			}
				    			if(input.equals("2")) {
				    				f = Faculty.FGIG;
				    			}
				    			if(input.equals("3")) {
				    				f = Faculty.FANGI;
				    			}
				    			if(input.equals("4")) {
				    				f = Faculty.BS;
				    			}
				    			if(input.equals("5")) {
				    				f = Faculty.ISE;
				    			}
				    			if(input.equals("6")) {
				    				f = Faculty.KMA;
				    			}
				    			if(input.equals("7")) {
				    				f = Faculty.NOCHI;
				    			}
				    			manager.createCourse(courseName, credits, desc, semac, f);
							}
							
							
							if(input.equals("2")) {
								System.out.println("Enter teacher's login");
								String login = reader.readLine();
								System.out.println("Enter course's name");
								String courseName = reader.readLine();
								manager.assignTeacherToCourse(login, courseName);
							}
							if(input.equals("3")) {
								manager.createStatisticalReport();
							}
							if(input.equals("4")) {
								System.out.println("Choose option: \n1. Add news\n2. Delete news ");
								String choosen = reader.readLine();
								if(choosen.equals("1")) {
									System.out.println("Enter news' title: ");
									String title = reader.readLine();
									System.out.println("Enter message");
									String message = reader.readLine();
									manager.addNews(title, message);
								}
								if(choosen.equals("2")) {
									System.out.println("Enter title of new that ypu want to delete: ");
									String title = reader.readLine();
									manager.removeNews(title);
								}
							}
							if(input.equals("5")) {
								manager.viewAllCourses();
							}
							if(input.equals("6")) {
								System.out.println("Write the login of employee you want to message to:");
					    		String login = reader.readLine();
					    		System.out.println("Write the message"); 
					    		String message = reader.readLine();
					    		manager.sendMessage(login, message);
							}
							if(input.equals("7")) {
								manager.getMessage();
							}
							if(input.equals("8")) {
								System.out.println("Enter the login of student");
								String login = reader.readLine();
								System.out.println("Choose one: \n1.APPROVE\n2.DENY");
								String choosen = reader.readLine();
								if(choosen.equals("1")) {
									manager.approveRegistration(login, "APPROVE");
								}
								else {
									manager.approveRegistration(login, "DENY");
								}
							}
							if(input.equals("9")) {
								manager.viewRequests();
							}
							if(input.equals("10")) {
								manager.viewNews();
							}
							if(input.equals("11")) {
								manager.orderStudentAlphabetically();
							}
							if(input.equals("12")) {
								manager.orderStudentByGpa();
							}
							if(input.equals("13")) {
								manager.orderTeacherAlphabetically();
							}
							if(input.equals("14")) {
								System.out.println("Write login of Student");
								String login = reader.readLine();
								System.out.println("Write course of lesson");
								String courseName = reader.readLine();
								System.out.println("Write name of Teacher of lesson");
								String teacherName = reader.readLine();
								System.out.println("Write the time of lesson in format {18, 9}");
								int time  = Integer.parseInt(reader.readLine());
								
								System.out.println("Choose the format of lessons :\n" + "1. PRACTISE\n2. LECTURE\n3. LAB WORK");
								input = reader.readLine();
								LessonType type = null;
								if(input.equals("1")) {
									type = LessonType.Practise;
								}
								if(input.equals("2")) {
									type = LessonType.Lecture;
								}
								if(input.equals("3")) {
									type = LessonType.laboratory;
								}
								
					    		System.out.println("Please, choose day of week :\n" + "1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday\n6. Saturday");
					    		input = reader.readLine();
					    		DayOfWeek day = null;
				    			if(input.equals("1")) {
				    				day = DayOfWeek.MON;
				    			}
				    			if(input.equals("2")) {
				    				day = DayOfWeek.TUE;
				    			}
				    			if(input.equals("3")) {
				    				day = DayOfWeek.WED;
				    			}
				    			if(input.equals("4")) {
				    				day = DayOfWeek.THU;
				    			}
				    			if(input.equals("5")) {
				    				day = DayOfWeek.FRI;
				    			}
				    			if(input.equals("6")) {
				    				day = DayOfWeek.SAT;
				    			}
				    			System.out.print("Enter the room");
				    			String room = reader.readLine();
								
								
								manager.assignLessonToStudent(login, courseName, teacherName, time, day, room, type);
							}
						}
					}
					
					
					
					
					if(user instanceof Librarian) {
						while(flag) {
							Librarian librarian = (Librarian)user;
							System.out.println("-----Kazakh-British Technical University-----");
							System.out.println("--------------Librarian's console--------------");
							System.out.println("Please, choose on of the functions:\n"+
											   "1 : Add book\n" +
											   "2 : Remove book\n"+
											   "3 : View requests\n" +
											   "4 : Manage requests\n" +
											   "5 : View news\n" +
											   "6 : View List of Books\n" + 
											   "7 : Send Message\n" +
											   "8 : View Message\n" +
											   "0 : Logging out");
							input = reader.readLine();
							if(input.equals("0")) {
								Databases.save();
								flag = false;
							}
							
							if(input.equals("1")) {
								System.out.println("Write the name of book");
								String nameOfBook = reader.readLine();
								System.out.println("Write the author of the book");
								String authorOfBook = reader.readLine();
								System.out.println("Write the description of the book");
								String descOfBook = reader.readLine();
								librarian.addBook(nameOfBook, authorOfBook, descOfBook);
							}
							if(input.equals("2")) {
								System.out.println("Write the name of book");
								String nameOfBook = reader.readLine();
								librarian.removeBook(nameOfBook);
							}
							if(input.equals("3")) {
								librarian.viewRequest();
							}
							if(input.equals("4")) {
								System.out.println("Enter the login of student");
								String login = reader.readLine();
								System.out.println("Choose one: \n1.APPROVE\n2.DENY");
								String choosen = reader.readLine();
								if(choosen.equals("1")) {
									librarian.acceptRequest(login, "APPROVE");
								}
								else {
									librarian.acceptRequest(login, "DENY");
								}
							}
							
							if(input.equals("5")) {
								librarian.viewNews();
							}
							if(input.equals("6")) {
								librarian.seeListOfBooks();
							}
							if(input.equals("7")) {
								System.out.println("Write the login of employee you want to message to:");
					    		String login = reader.readLine();
					    		System.out.println("Write the message"); 
					    		String message = reader.readLine();
					    		librarian.sendMessage(login, message);
							}
							if(input.equals("8")) {
								librarian.getMessage();
							}
						}
					}
				}
			}
		}
		
	
	}

}
