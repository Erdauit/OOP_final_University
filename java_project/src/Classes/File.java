package Classes;

import java.io.Serializable;

public class File implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private String courseName;
	private String fileName;
    private String description;
    private String fromWho;
    
    
    public File(String courseName, String fileName, String description, String from) {
		super();
		this.courseName = courseName;
		this.fileName = fileName;
		this.description = description;
		this.fromWho = from;
	}
    
	//GETTERS and SETTERS
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getFromWho() {
		return fromWho;
	}

	public void setFromWho(String fromWho) {
		this.fromWho = fromWho;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
    
    
}
