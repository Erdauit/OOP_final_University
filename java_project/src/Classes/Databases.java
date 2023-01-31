package Classes;

import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import People.*;

@SuppressWarnings("serial")
public class Databases implements Serializable{
	//FIELDS
	//Singleton pattern
	public static Vector<User> users = new Vector <User>();
    public static Vector<Course> courses = new Vector <Course>();
    public static Vector<News> news = new Vector <News>();    
    public static Vector<File> files = new Vector<File>();
    public static Vector<Message> messages = new Vector<Message>();
    public static Vector<Organizations> organizations = new Vector <Organizations>();
    public static Vector<Request> requests = new Vector <Request>();
    public static HashMap<Student, HashMap<Course, Mark>> marks; 
    public static HashSet<Book> books = new HashSet <Book>();
    public static Vector <LogFiles> logFiles = new Vector <LogFiles>();
    

    
    
    
    public static void load() {
		loadUsers();		
		loadCourses();
		loadNews();
		loadFiles();
		loadMessages();
		loadRequests();
		loadOrganizations();
		loadLogFiles();
		loadBooks();
    }
    
    public static void save() {
		saveUsers();
		saveNews();
		saveCourses();
		saveFiles();
		saveMessages();
		saveRequests();
		saveOrganizations();
		saveBooks();
		saveLogFiles();
    }
  
    
    
    
    
    
    @SuppressWarnings({ "unused", "resource" })
	public static void saveUsers() {
    	
        try {
        	FileOutputStream fileOut = new FileOutputStream("users.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(users);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("users.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadUsers() {
    	try {
    		FileInputStream fis = new FileInputStream("users.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            users = (Vector<User>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
            users = new Vector<>();
            System.err.println("users.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            users = new Vector<>();
            System.err.println("users.txt: ClassNotFoundException");
        }   
    }
	
    
    @SuppressWarnings({ "unused", "resource" })
	public static void saveLogFiles() {
    	
        try {
        	FileOutputStream fileOut = new FileOutputStream("logFiles.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(logFiles);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("logFiles.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadLogFiles() {
    	try {
    		FileInputStream fis = new FileInputStream("logFiles.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
    		logFiles = (Vector<LogFiles>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
            users = new Vector<>();
            System.err.println("logFiles.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            users = new Vector<>();
            System.err.println("logFiles.txt: ClassNotFoundException");
        }   
    }
	
    
    @SuppressWarnings({ "unused", "resource" })
	public static void saveBooks() {
    	
        try {
        	FileOutputStream fileOut = new FileOutputStream("books.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(books);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("books.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadBooks() {
    	try {
    		FileInputStream fis = new FileInputStream("books.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            books = (HashSet<Book>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
            users = new Vector<>();
            System.err.println("books.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            users = new Vector<>();
            System.err.println("books.txt: ClassNotFoundException");
        }   
    }
	
	
	
	public static void saveOrganizations() {
        try {
        	FileOutputStream fileOut = new FileOutputStream("organizations.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(organizations);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("organizations.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadOrganizations() {
    	try {
    		FileInputStream fis = new FileInputStream("organizations.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
    		organizations = (Vector<Organizations>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
        	organizations = new Vector<>();
            System.err.println("organizations.txt: IOException");
        }
        catch (ClassNotFoundException e) {
        	organizations = new Vector<>();
            System.err.println("organizations.txt: ClassNotFoundException");
        }   
    }
	
	public static void saveRequests() {
        try {
        	FileOutputStream fileOut = new FileOutputStream("requests.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(requests);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("requests.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadRequests() {
    	try {
    		FileInputStream fis = new FileInputStream("requests.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
    		requests = (Vector<Request>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
        	requests = new Vector<>();
            System.err.println("requests.txt: IOException");
        }
        catch (ClassNotFoundException e) {
        	requests = new Vector<>();
            System.err.println("requests.txt: ClassNotFoundException");
        }   
    }
	
	
    @SuppressWarnings("unused")
	private static void saveCourses() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("courses.txt"))) {
            oot.writeObject(courses);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("courses.txt: IOException");
        }
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    
    
	private static void loadCourses(){
    	try {
    		FileInputStream fis = new FileInputStream("courses.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            courses = (Vector<Course>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (ClassNotFoundException e) {
            courses = new Vector<>();
            System.err.println("courses.txt: ClassNotFoundException");
        }
        catch (IOException e) {
            courses = new Vector<>();
            System.err.println("courses.txt: IOException");
        }
    }
    
	public static void saveNews() {
    	
        try {
        	FileOutputStream fileOut = new FileOutputStream("news.txt");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(news);
            out.flush();
            out.close();
            fileOut.close();
        }
        catch (IOException e) {
            System.err.println("news.txt: IOException");
        }
    }
    
	@SuppressWarnings("unchecked")
	public static void loadNews() {
    	try {
    		FileInputStream fis = new FileInputStream("news.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            news = (Vector<News>) oin.readObject();
            oin.close();
            fis.close();
    	}
        catch (IOException e) {
            news = new Vector<>();
            System.err.println("news.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            news = new Vector<>();
            System.err.println("news.txt: ClassNotFoundException");
        }   
    }
	
	
    @SuppressWarnings("unused")
	private static void saveFiles() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("files.txt"))) {
            oot.writeObject(files);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("files.txt: IOException");
        }
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    
    
	private static void loadFiles(){
    	try {
    		FileInputStream fis = new FileInputStream("files.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            files = (Vector<File>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (ClassNotFoundException e) {
            files = new Vector<>();
            System.err.println("files.txt: ClassNotFoundException");
        }
        catch (IOException e) {
            files = new Vector<>();
            System.err.println("files.txt: IOException");
        }
    }
    
    @SuppressWarnings("unused")
	private static void saveMessages() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("messages.txt"))) {
            oot.writeObject(messages);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("messages.txt: IOException");
        }
    }
    
    @SuppressWarnings({ "unchecked", "unused" })
    
    
	private static void loadMessages(){
    	try {
    		FileInputStream fis = new FileInputStream("messages.txt");
    		ObjectInputStream oin = new ObjectInputStream(fis); 
            messages = (Vector<Message>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (ClassNotFoundException e) {
            files = new Vector<>();
            System.err.println("messages.txt: ClassNotFoundException");
        }
        catch (IOException e) {
            files = new Vector<>();
            System.err.println("messages.txt: IOException");
        }
    }
	
}
