package com.masai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Appointment;
import com.masai.service.AppointmentService;

@RestController

public class DemoAppointmentController {

	private AppointmentService appointmentService;

	public DemoAppointmentController(AppointmentService appointmentService) {
		super();
		this.appointmentService = appointmentService;
	}

	@GetMapping("/appointments")
	public List<Appointment> getVaccineAllAppointment() {
		return appointmentService.getAllAppointment();
	}

	@GetMapping("/appointment/{bookId}")
	public Appointment getVaccineAppointmentByBookingId(@PathVariable("bookId") long bookingId) {
		return appointmentService.getAppointmentByBookingId(bookingId);
	}

}
