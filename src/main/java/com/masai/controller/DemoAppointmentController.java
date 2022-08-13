package com.masai.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Appointment;
import com.masai.service.AppointmentService;

@RestController

public class DemoAppointmentController {
 
	private AppointmentService  appointmentService;

	public DemoAppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}
	
	@PostMapping("/appointment")
	public Appointment saveVaccineAppointment(@RequestBody Appointment app) {
		
		return appointmentService.addAppointment(app);
	}
	
	@GetMapping("/appointment")
	public List<Appointment> getVaccineAllAppointment(){
		return appointmentService.getAllAppointment();
	}
	
	@GetMapping("/appointment/{bookId}")
	public Appointment getVaccineAppointmentByBookingId(@PathVariable("bookId") long bookingId) {
		return appointmentService.getAppointmentByBookingId(bookingId);
	}
	
	@PostMapping("/appointment/{bookId}")
	public Appointment updateVaccineAppointment(@RequestBody Appointment app) {
		return appointmentService.updateAppointment(app);
	}
	
	@DeleteMapping("/appointment")
	public boolean deleteVaccineAppointment(@RequestBody Appointment app) {
		return appointmentService.deleteAppointment(app);
	}
}
