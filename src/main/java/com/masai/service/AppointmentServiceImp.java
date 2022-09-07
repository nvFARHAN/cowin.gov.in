package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AppointmentExcepation;
import com.masai.exceptions.AppointmentNotFoundExecpation;
import com.masai.model.Appointment;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Member;
import com.masai.model.VaccinationCenter;
import com.masai.model.VaccineRegistration;
import com.masai.repository.AdminSessionDAO;
import com.masai.repository.AppointmentDao;
import com.masai.repository.UserSessionDAO;

@Service
public class AppointmentServiceImp implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;

	@Autowired
	private VaccineRegistrationService registrationService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	@Autowired
	private AdminSessionDAO adminSessionDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public List<Appointment> getAllAppointment(String key) {

		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		List<Appointment> appointments = appointmentDao.findAll();
		if (appointments.size() > 0)
			return appointments;
		else
			throw new AppointmentExcepation("No appointment found");
	}

	
	
	@Override
	public Appointment getAppointmentByBookingId(Long bookingId,String key) {

		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()&&!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
			
		return appointmentDao.findById(bookingId)
				.orElseThrow(() -> new AppointmentNotFoundExecpation("Appointment not found by same booking id!"));
	}

	@Override
	public Appointment addAppointment(Appointment app, Integer memId,String key) {
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
	

		VaccineRegistration reg = registrationService.getVaccineRegistration(app.getMobileNo(),key);
		if (reg == null)
			throw new AppointmentExcepation("First do the registration...");
		else {
			List<Member> list = reg.getMembers();
			for (Member m : list) {
				if (m.getMemberId() == memId) {
					app.setMember(m);
					app.setDateofbooking(LocalDate.now());
					app.setBookigStatus(true);
					Integer id = app.getVaccinationCenter().getCode();
					VaccinationCenter vaccinationCenter = vaccinationCenterService.getVaccineCenter(id,key);
					app.setVaccinationCenter(vaccinationCenter);
					Appointment a = appointmentDao.save(app);
					m.getAppointments().add(a);
					memberService.updateMember(m, m.getMemberId(),key);
					return a;
				}
			}
			throw new AppointmentExcepation("Member not found...");
		}
	}

	@Override
	public Appointment updateAppointment(Appointment app,String key) {
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		Appointment appointment = appointmentDao.findById(app.getBookingID())
				.orElseThrow(() -> new AppointmentExcepation("Appointment not found!"));

		appointment.setDateofbooking(app.getDateofbooking());
		appointment.setVaccinationCenter(app.getVaccinationCenter());
		appointment.setSlot(app.getSlot());
		return appointmentDao.save(appointment);
	}

	@Override
	public boolean deleteAppointment(Long bookingId,String key) {
		
		 Optional<CurrentUserSession> optCurrUser= userSessionDAO.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		Appointment ExitApp = appointmentDao.findById(bookingId)
				.orElseThrow(() -> new AppointmentExcepation("Appointment not found!"));
		appointmentDao.delete(ExitApp);
		return true;

	}

}
