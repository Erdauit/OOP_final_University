package Classes;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	 private String name;
	 private String description;
	 private String author;
	 
	 public Book() {}
	 
	 public Book(String name, String desc, String author) {
		super();
		this.name = name;
		this.author = author;
	}
	 
	//GETTERS and SETTERS
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, description, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", description=" + description + ", author=" + author + "]";
	}
	
	
	 
	 
}
