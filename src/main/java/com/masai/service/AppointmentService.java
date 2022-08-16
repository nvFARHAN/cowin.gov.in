package com.masai.service;

import java.util.List;

import com.masai.exceptions.AppointmentExcepation;
import com.masai.exceptions.AppointmentNotFoundExecpation;
import com.masai.model.Appointment;

public interface AppointmentService {

	public List<Appointment> getAllAppointment()throws AppointmentExcepation;

	public Appointment getAppointmentByBookingId(Long bookingId)throws AppointmentNotFoundExecpation;

	public Appointment addAppointment(Appointment app, Integer memId)throws AppointmentExcepation;

	public Appointment updateAppointment(Appointment app)throws AppointmentExcepation;

	public boolean deleteAppointment(Long bookingId)throws AppointmentExcepation;

}
