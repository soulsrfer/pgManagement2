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

import com.gopalpg.pgManagement.entity.BedEntity;
import com.gopalpg.pgManagement.service.BedService;

@RestController
@RequestMapping("/api")
public class BedController {

	private BedService bedService;

	public BedController(BedService bedService) {
		super();
		this.bedService = bedService;
	}

	// build create bed REST API

	@PostMapping(value = "/createBed/{roomId}")
	public ResponseEntity<BedEntity> saveBed(@PathVariable(value = "roomId") Long id, @RequestBody BedEntity bed) {
		return new ResponseEntity<BedEntity>(bedService.saveBed(bed, id), HttpStatus.CREATED);
	}

	// build get all beds REST API
	@GetMapping("/beds")
	public List<BedEntity> getAllBeds() {
		return bedService.getAllBeds();
	}

	// build get Bed by id REST API
	@GetMapping("/getBed/{bedId}")
	public ResponseEntity<BedEntity> getBedById(@PathVariable(value = "bedId") Long BedId) {
		return new ResponseEntity<BedEntity>(bedService.getBedById(BedId), HttpStatus.OK);
	}
	
	//build update bed REST API
	@PutMapping("/updateBed/{bedId}")
	public ResponseEntity<BedEntity> updateBed(@PathVariable("bedId") Long BedId, @RequestBody BedEntity bed){
		return new ResponseEntity<BedEntity>(bedService.updateBed(bed, BedId), HttpStatus.OK);
	}
	
	//build delete Bed REST API
	@DeleteMapping("/deleteBed/{bedId}")
	public ResponseEntity<String> deleteBed(@PathVariable(value = "bedId") Long BedId){
		bedService.deleteBed(BedId);
		return new ResponseEntity<String>("Bed deleted successfully", HttpStatus.OK);
	}
	

}
