package com.masai.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AppointmentExcepation;
import com.masai.exceptions.AppointmentNotFoundExecpation;
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

		return appointmentDao.findById(bookingId)
                .orElseThrow(()->  new AppointmentNotFoundExecpation("Appointment not found by same booking id!") );
	}

	@Override
	
	public Appointment addAppointment(Appointment app) {
<<<<<<< HEAD
		
=======
		Optional<Appointment> appExit=appointmentDao.findById(app.getBookingID());
		
		if(appExit.isPresent()) throw new AppointmentExcepation("Appointment is  already saved!");
>>>>>>> 69c1eaa2c5258745a92a442dc5af299f74e5ee6d
		return appointmentDao.save(app);
		//return app;
	}

	@Override
	public Appointment updateAppointment(Appointment app) {
		Appointment ExitApp = appointmentDao.findById(app.getBookingID())
                              .orElseThrow(()->  new AppointmentExcepation("Appointment not found!") );
	
			appointmentDao.save(app);
			return app;
	
	}

	@Override
	public boolean deleteAppointment(Appointment app) {
		Appointment ExitApp = appointmentDao.findById(app.getBookingID())
                .orElseThrow(()->  new AppointmentExcepation("Appointment not found!") );
			appointmentDao.delete(ExitApp);
			return true;
		
	}

}
