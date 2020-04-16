package com.blasanka.helthcare.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.blasanka.helthcare.database.DatabaseRef;
import com.blasanka.helthcare.exceptions.DataNotFoundException;
import com.blasanka.helthcare.models.Appointment;
import com.blasanka.helthcare.models.User;

public class AppointmentController {
	
	private Map<Long, Appointment> appointments = DatabaseRef.getAppointments();

	public AppointmentController() {
	}
	
	public List<Appointment> getAppointments() {
		return new ArrayList<Appointment>(appointments.values());
	}

	public List<Appointment> getSortedAppointments(final String type) {
		List<Appointment> appoints = new ArrayList<Appointment>(appointments.values());
		Collections.sort(appoints, new Comparator<Appointment>() {
		  public int compare(Appointment o1, Appointment o2) {
			  if (type.equals("no")) return Long.compare(o1.getAppointNo(), o2.getAppointNo());
			  else if (type.equals("doctor")) return Long.compare(o1.getDoctorId(), o2.getDoctorId());
			  else if (type.equals("user")) return Long.compare(o1.getUserId(), o2.getUserId());
			  else if (type.equals("hospital")) return Long.compare(o1.getHospitalId(), o2.getHospitalId());
		      return o1.getDate().compareTo(o2.getDate());
		  }
		});
		return appoints;
	}

	public List<Appointment> getAppointmentsPaginated(int start, int size) {
		List<Appointment> appoints = new ArrayList<Appointment>(appointments.values());
		if (start + size > appoints.size()) return new ArrayList<Appointment>();
		return appoints.subList(start, start + size);
	}
	
	public Appointment getAppointment(long id) {
		Appointment appointment = appointments.get(id);
		if (appointment == null)
			throw new DataNotFoundException("Appointment with id: " + id + " not found!");
		return appointment;
	}

	public Appointment addAppointment(Appointment appointment) {
		appointment.setAppointId(appointments.size() + 1);
		appointments.put(appointment.getAppointId(), appointment);
		return appointment;
	}
	
	public Appointment updateAppointment(Appointment appointment) {
		if (appointment.getAppointId() <= 0) return null;
		appointments.put(appointment.getAppointId(), appointment);
		return appointment;
	}
	
	public Appointment removeAppointment(long id) {
		return appointments.remove(id);
	}
	
}
