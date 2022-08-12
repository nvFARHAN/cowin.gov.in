package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Appointment;
import com.masai.repository.AppointmentDao;

@Service
public class AppointmentServiceImp implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;

	public AppointmentServiceImp(AppointmentDao appointmentDao) {
		super();
		this.appointmentDao = appointmentDao;
	}

	@Override
	public List<Appointment> getAllAppointment() {

		return appointmentDao.findAll();
	}

	@Override
	public Appointment getAppointmentByBookingId(long bookingId) {

		Appointment ExitApp = appointmentDao.findByBookingId(bookingId);

		if (ExitApp != null) {
			return ExitApp;
		} else
			throw new RuntimeException("Appointment not found!");

	}

	@Override
	public Appointment addAppointment(Appointment app) {
		return appointmentDao.save(app);
	}

	@Override
	public Appointment updateAppointment(Appointment app) {
		Appointment ExitApp = appointmentDao.findByBookingId(app.getBookingID());

		if (ExitApp != null) {
			appointmentDao.save(app);
			return app;
		} else
			throw new RuntimeException("Appointment not found!");

	}

	@Override
	public boolean deleteAppointment(Appointment app) {
		Appointment ExitApp = appointmentDao.findByBookingId(app.getBookingID());

		if (ExitApp != null) {
			appointmentDao.delete(ExitApp);
			return true;
		} else
			throw new RuntimeException("Appointment not found!");

	}

}
