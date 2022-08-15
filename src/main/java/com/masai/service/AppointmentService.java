package com.masai.service;

import java.util.List;

import com.masai.model.Appointment;

public interface AppointmentService {

	public List<Appointment> getAllAppointment();

	public Appointment getAppointmentByBookingId(Long bookingId);

	public Appointment addAppointment(Appointment app, Integer memId);

	public Appointment updateAppointment(Appointment app);

	public boolean deleteAppointment(Long bookingId);

}
