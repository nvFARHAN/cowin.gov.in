package com.masai.service;

import java.util.List;

import com.masai.model.Appointment;

public interface AppointmentService {
	
	public List<Appointment> getAllAppointment();

	public Appointment getAppointmentByBookingId(long bookingId);

	public Appointment addAppointment(Appointment app);

	public Appointment updateAppointment(Appointment app);

	public boolean deleteAppointment(Appointment app);

}
