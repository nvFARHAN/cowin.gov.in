package com.masai.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.Appointment;
import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.VaccinationCenter;
import com.masai.model.Vaccine;
import com.masai.model.VaccineInventory;
import com.masai.service.AdminServiceImpl;
import com.masai.service.AppointmentService;
import com.masai.service.IdCardService;
import com.masai.service.MemberService;
import com.masai.service.VaccinationCenterService;
import com.masai.service.VaccineInventoryService;
import com.masai.service.VaccineService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private IdCardService idservice;

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@Autowired
	private VaccineInventoryService vaccineInvService;

	@Autowired
	private VaccinationCenterService vaccinationCenterService;

	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private IdCardService idCardService;

	// to register admin
	@PostMapping("/")
	public Admin saveAdmin(@RequestBody Admin admin) {
		return adminServiceImpl.createAdmin(admin);
	}

	// to update admin by passing key
	@PutMapping("/update")
	public Admin updateAdmin(@RequestBody Admin admin, @RequestParam(required = false) String key) {

		return adminServiceImpl.updateAdmin(admin, key);
	}

	@PostMapping("/vaccine_inventory")
	public ResponseEntity<VaccineInventory> saveVaccineHandler(@RequestBody VaccineInventory vaccineInv) {

		return new ResponseEntity<>(vaccineInvService.saveVaccineInventory(vaccineInv), HttpStatus.CREATED);

	}

	@GetMapping("/vaccine_inventory")
	public ResponseEntity<List<VaccineInventory>> getAllInventoryHandler() {
		return new ResponseEntity<List<VaccineInventory>>(vaccineInvService.allVaccineInventory(), HttpStatus.FOUND);
	}

	@GetMapping("/vaccine_inventory/centerid/{centerid}")
	public ResponseEntity<VaccineInventory> getVaccineInventoryByCenterHandler(
			@PathVariable("centerid") Integer centerid) {

		return new ResponseEntity<VaccineInventory>(vaccineInvService.getVaccineInventoryByCenter(centerid),
				HttpStatus.FOUND);

	}

	@PutMapping("/vaccine_count/{vaccineId}")
	public ResponseEntity<VaccineInventory> addVaccineCountHandler(@RequestBody VaccineInventory inv,
			@PathVariable("vaccineId") Integer vaccineId) {

		return new ResponseEntity<VaccineInventory>(vaccineInvService.addVaccineCount(inv, vaccineId), HttpStatus.OK);
	}

	@PutMapping("/vaccine_inventory")
	public ResponseEntity<VaccineInventory> updateVaccineInventoryHandler(
			@RequestBody VaccineInventory vaccineInventory) {

		return new ResponseEntity<VaccineInventory>(vaccineInvService.updateVaccineInventory(vaccineInventory),
				HttpStatus.OK);
	}

	@DeleteMapping("/vaccine_inventory")
	public ResponseEntity<String> deleteVaccineInventory(@RequestBody VaccineInventory inv) {
		return new ResponseEntity<String>(
				"Vaccine Inventory Deleted : " + vaccineInvService.deleteVaccineInventory(inv), HttpStatus.OK);
	}

	@GetMapping("/vaccine_inventory/date/{date}")
	public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByDate(
			@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

		return new ResponseEntity<List<VaccineInventory>>(vaccineInvService.getVaccineInventoryByDate(date),
				HttpStatus.FOUND);
	}

	@GetMapping("/vaccine_inventory/vaccinename/{vaccineName}")
	public ResponseEntity<List<VaccineInventory>> getVaccineInventoryByVaccine(
			@PathVariable("vaccineName") String vaccineName) {

		return new ResponseEntity<List<VaccineInventory>>(vaccineInvService.getVaccineInventoryByVaccine(vaccineName),
				HttpStatus.FOUND);
	}

	// vaccine
	@GetMapping("/vaccine")
	public ResponseEntity<List<Vaccine>> getAllVaccines() {
		return new ResponseEntity<List<Vaccine>>(vaccineService.allVaccine(), HttpStatus.FOUND);
	}

	@GetMapping("/vaccine/{id}")
	public ResponseEntity<Vaccine> getVaccineById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Vaccine>(vaccineService.getVaccineById(id), HttpStatus.FOUND);
	}

	@GetMapping("/vaccine/neme/{name}")
	public ResponseEntity<Vaccine> getVaccineByName(@PathVariable("name") String name) {
		return new ResponseEntity<Vaccine>(vaccineService.getVaccineByName(name), HttpStatus.FOUND);

	}

	@PostMapping("/vaccine")
	public ResponseEntity<Vaccine> addVaccine(@RequestBody Vaccine vaccine) {
		return new ResponseEntity<Vaccine>(vaccineService.addVaccine(vaccine), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/vaccine")
	public ResponseEntity<String> deleteVaccine(@RequestBody Vaccine vaccine) {
		return new ResponseEntity<String>("Vaccine deleted : " + vaccineService.deleteVaccine(vaccine), HttpStatus.OK);
	}

	@PutMapping("/vaccine")
	public ResponseEntity<Vaccine> updateVaccine(@RequestBody Vaccine vaccine) {
		return new ResponseEntity<Vaccine>(vaccineService.updateVaccine(vaccine), HttpStatus.OK);
	}

	// vaccineCenter
	@GetMapping("/vaccination_centers")
	public ResponseEntity<List<VaccinationCenter>> getVaccineCenters() {
		return new ResponseEntity<List<VaccinationCenter>>(vaccinationCenterService.allVaccineCenters(), HttpStatus.OK);
	}

	@PostMapping("/vaccination_center")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@RequestBody VaccinationCenter center) {
		return new ResponseEntity<VaccinationCenter>(vaccinationCenterService.addVaccineCenter(center),
				HttpStatus.CREATED);
	}

	@GetMapping("/vaccination_center/{id}")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@PathVariable("id") Integer id) {
		return new ResponseEntity<VaccinationCenter>(vaccinationCenterService.getVaccineCenter(id), HttpStatus.FOUND);
	}

	@PutMapping("/vaccination_center")
	public ResponseEntity<VaccinationCenter> updateVaccineCenter(@RequestBody VaccinationCenter center) {
		return new ResponseEntity<VaccinationCenter>(vaccinationCenterService.updateVaccineCenter(center),
				HttpStatus.OK);
	}

	@DeleteMapping("/vaccination_center")
	public ResponseEntity<String> deleteVaccineCenter(@RequestBody VaccinationCenter center) {
		return new ResponseEntity<>("vaccine center deleted : " + vaccinationCenterService.deleteVaccineCenter(center),
				HttpStatus.OK);
	}

	// appointment

	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getVaccineAllAppointment() {
		return new ResponseEntity<List<Appointment>>(appointmentService.getAllAppointment(), HttpStatus.FOUND);
	}

	@GetMapping("/appointment/{bookId}")
	public ResponseEntity<Appointment> getVaccineAppointmentByBookingId(@PathVariable("bookId") long bookingId) {
		return new ResponseEntity<Appointment>(appointmentService.getAppointmentByBookingId(bookingId),
				HttpStatus.FOUND);
	}

	// member
	@PutMapping("/member/dose/{memId}")
	public ResponseEntity<Member> updateDoseStatus(@RequestBody Member member, @PathVariable("memId") Integer memId) {
		return new ResponseEntity<Member>(memberService.updatedoseStatus(member, memId), HttpStatus.OK);
	}

	@GetMapping("/member/{id}")
	public ResponseEntity<Member> getMember(@PathVariable("id") Integer idCardId) {
		return new ResponseEntity<Member>(memberService.getMemberById(idCardId), HttpStatus.FOUND);
	}

	@GetMapping("/member/aadhar/{aadharNo}")
	public ResponseEntity<Member> getMemberByAadhar(@PathVariable("aadharNo") Long aadharNo) {
		return new ResponseEntity<Member>(memberService.getMemberByAdharNo(aadharNo), HttpStatus.FOUND);
	}

	@GetMapping("/member/pan/{panNo}")
	public ResponseEntity<Member> getMemberByAadhar(@PathVariable("panNo") String panNo) {
		return new ResponseEntity<Member>(memberService.getMemberByPanNo(panNo), HttpStatus.FOUND);
	}

	@DeleteMapping("/member")
	public ResponseEntity<String> deleteMemberRecord(@RequestBody Member member) {
		return new ResponseEntity<String>("Memeber record deleted : " + memberService.deleteMemberRecord(member),
				HttpStatus.OK);
	}

	@PutMapping("/member/{id}")
	public ResponseEntity<Member> updateMember(@RequestBody Member member, @PathVariable("id") Integer mId) {
		return new ResponseEntity<Member>(memberService.updateMember(member, mId), HttpStatus.FOUND);
	}

	// idCard
	@GetMapping("/idcard/aadhhar/{id}")
	public ResponseEntity<IdCard> getIdcardByAddhar(@PathVariable("id") Long id) {
		return new ResponseEntity<IdCard>(idCardService.getIdCardByAdharNo(id), HttpStatus.FOUND);
	}

	@GetMapping("/idcard/pan/{id}")
	public ResponseEntity<IdCard> getIdcardByAddhar(@PathVariable("id") String id) {
		return new ResponseEntity<IdCard>(idCardService.getIdcardByPanNo(id), HttpStatus.FOUND);
	}

}
