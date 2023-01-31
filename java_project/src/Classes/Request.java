package Classes;

import java.io.Serializable;

import Enums.*;

public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//FIELDS
	private RequestType type;
    private String from;
    private Boolean approve;
    private String nameOfRequest;
    
    
    public Request(RequestType type, String from, Boolean approve, String nameOfRequest) {
		super();
		this.type = type;
		this.from = from;
		this.approve = approve;
		this.nameOfRequest = nameOfRequest;
	}
	//GETTERS and SETTERS
	public RequestType getType() {
		return type;
	}
	public void setType(RequestType type) {
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Boolean getApprove() {
		return approve;
	}
	public void setApprove(Boolean approve) {
		this.approve = approve;
	}
     
    public String getNameOfRequest() {
		return nameOfRequest;
	}
	public void setNameOfRequest(String nameOfRequest) {
		this.nameOfRequest = nameOfRequest;
	}
	//FUNCTION
	public void returnRequest(Boolean status) {
    }
}
