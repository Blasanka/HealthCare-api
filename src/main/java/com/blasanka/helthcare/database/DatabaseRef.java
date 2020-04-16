package com.blasanka.helthcare.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.blasanka.helthcare.models.Appointment;
import com.blasanka.helthcare.models.User;

public class DatabaseRef {

	private static Map<Long, Appointment> appointments = new HashMap<>();
	private static Map<Long, User> users = new HashMap<>();
	
	public DatabaseRef() {
	}

	public static Map<Long, Appointment> getAppointments() {
		appointments.put(1L, new Appointment(1L, 1L, 1L, 1L, 2L, new Date()));
		appointments.put(2L, new Appointment(2L, 2L, 2L, 2L, 3L, new Date()));
		return appointments;
	}
	
	public static Map<Long, User> getUsers() {
		users.put(1L, new User(1L, "BLA", "bla@gmail.com", "abc123", new Date()));
		users.put(2L, new User(2L, "leo", "leo@gmail.com", "123", new Date()));
		return users;
	}
}
