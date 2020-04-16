package com.blasanka.helthcare.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Appointment {

	private long appointId;
	private long appointNo;
	private long doctorId;
	private long userId;
	private long hospitalId;
	private Date date;

	public Appointment() {
		super();
	}

	public Appointment(long appointId, long appointNo, long doctorId, long userId, long hospitalId, Date date) {
		super();
		this.appointId = appointId;
		this.appointNo = appointNo;
		this.doctorId = doctorId;
		this.userId = userId;
		this.hospitalId = hospitalId;
		this.date = date;
	}


	public long getAppointId() {
		return appointId;
	}


	public void setAppointId(long appointId) {
		this.appointId = appointId;
	}


	public long getAppointNo() {
		return appointNo;
	}


	public void setAppointNo(long appointNo) {
		this.appointNo = appointNo;
	}


	public long getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
