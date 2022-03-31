package com.gopalpg.pgManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopalpg.pgManagement.entity.ReservationEntity;
import com.gopalpg.pgManagement.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	// build create Reservation REST API

	@PostMapping(value = "/createReservation/{userId}")
	public ResponseEntity<ReservationEntity> saveReservation(@PathVariable(value = "userId") Long id,
			@RequestBody ReservationEntity reservation) {
		return new ResponseEntity<ReservationEntity>(reservationService.saveReservation(reservation, id),
				HttpStatus.CREATED);

	}

	// build get all REST API
	@GetMapping("/getAllreservations")
	public List<ReservationEntity> getAllReservation() {
		return reservationService.getAllReservations();
	}

	// build get Reservation by id REST API
	@GetMapping("/getReservation/{reservationId}")
	public ResponseEntity<ReservationEntity> getReservationbyId(@PathVariable(value = "reservationId") Long ReservationId) {
		return new ResponseEntity<ReservationEntity>(reservationService.getReservationById(ReservationId),
				HttpStatus.OK);
	}

	// build update Reservation REST API
	@PutMapping("/updateReservation/{reservationId}")
	public ResponseEntity<ReservationEntity> updateReservation(@PathVariable(value = "reservationId") Long ReservationId,
			@RequestBody ReservationEntity reservation) {
		return new ResponseEntity<ReservationEntity>(reservationService.updateReservation(reservation, ReservationId),
				HttpStatus.OK);
	}
	//build delete Reservation REST API
	@DeleteMapping("/deleteReservation/{reservationId}")
	public ResponseEntity<String> deleteReservation(@PathVariable(value = "reservationId") Long ReservationId){
		reservationService.deleteReservation(ReservationId);
		return new ResponseEntity<String>("Reservation deleted successfully", HttpStatus.OK);
	}
	
	//build assign bed to reservation REST API
	@PutMapping("/reserveBed/{reservationId}/{bedId}")
	public ResponseEntity<ReservationEntity> reserveBed(@PathVariable(value = "reservationId") Long ReserveId,@PathVariable(value = "bedId") Long BedId){
		return new ResponseEntity<ReservationEntity>(reservationService.reserveBed(ReserveId, BedId), HttpStatus.OK);
	}
	
	
}
















