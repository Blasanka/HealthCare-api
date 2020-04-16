package com.blasanka.helthcare.resources.beans;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;


public class AppointmentFilterBean {

	@HeaderParam("username") String username;
	@QueryParam("sort") String type;
	@QueryParam("start") int start;
	@QueryParam("size") int size;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
