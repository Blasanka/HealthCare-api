package com.blasanka.helthcare.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.blasanka.helthcare.models.Appointment;
import com.blasanka.helthcare.models.User;

public class DatabaseRef {
	
	private Connection connection = null;
	private static Map<Long, User> users = new HashMap<>();
	
	public DatabaseRef() {
		String url = "jdbc:mysql://localhost:3306/helth_care?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Long, Appointment> getAppointments(String type, long id) {
		
		Map<Long, Appointment> appointments = new HashMap<>();
		String sql = "";
		
		PreparedStatement statement;
		try {
			
			switch (type) {
				case "patient":
					sql = "SELECT * FROM appointments WHERE patient_id = " + id;
					statement = connection.prepareStatement(sql);
					break;
				case "doctor":
					sql = "SELECT * FROM appointments WHERE doctor_id = " + id;
					statement = connection.prepareStatement(sql);
					break;
				case "hospital":
					sql = "SELECT * FROM appointments WHERE hospital_id = " + id;
					statement = connection.prepareStatement(sql);
					break;
				default:
					sql = "SELECT * FROM appointments";
					statement = connection.prepareStatement(sql);
					break;
			}
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Appointment appointment = new Appointment(
					rs.getLong(1),
					rs.getLong(2),
					rs.getLong(3),
					rs.getLong(4),
					rs.getLong(5),
					rs.getDate(6),
					rs.getDate(7),
					rs.getDate(8)
				);
				appointments.put(appointment.getAppointId(), appointment);
			}
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appointments;
	}

	public Appointment getAppointment(long id) {
		
		Appointment appointment = new Appointment();
		String sql = "SELECT * FROM appointments WHERE appointId='"+ id +"'";
		
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				appointment = new Appointment(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						rs.getLong(4),
						rs.getLong(5),
						rs.getDate(6),
						rs.getDate(7),
						rs.getDate(8)
					);
			}
		    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appointment;
	}
	

	public int addAppointment(Appointment appointment) {
		String sql = "INSERT INTO appointments(doctor_id, patient_id, hospital_id, user_id, date, time, created_at) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement statement;
		int affectedRows = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, appointment.getDoctorId());
			statement.setLong(2, appointment.getPatientId());
			statement.setLong(3, appointment.getHospitalId());
			statement.setLong(4, appointment.getUserId());
			statement.setDate(5, appointment.getDate());
			statement.setDate(6, appointment.getDate());
			statement.setDate(7, new Date(System.currentTimeMillis()));
			affectedRows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public static Map<Long, User> getUsers() {
		users.put(1L, new User(1L, "BLA", "bla@gmail.com", "abc123", new Date(System.currentTimeMillis())));
		users.put(2L, new User(2L, "leo", "leo@gmail.com", "123", new Date(System.currentTimeMillis())));
		return users;
	}
}
