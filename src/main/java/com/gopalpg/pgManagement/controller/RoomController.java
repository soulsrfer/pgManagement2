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

import com.gopalpg.pgManagement.entity.RoomEntity;
import com.gopalpg.pgManagement.service.RoomService;

@RestController
@RequestMapping("/api")
public class RoomController {

	private RoomService roomService;

	public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}

	// build create user REST API

	@PostMapping("/createRoom")
	public ResponseEntity<RoomEntity> saveRoom(@RequestBody RoomEntity room) {
		return new ResponseEntity<RoomEntity>(roomService.saveRoom(room), HttpStatus.CREATED);
	}

	// build get all rooms REST API
	@GetMapping("/getAllRooms")
	public List<RoomEntity> getAllUsers() {
		return roomService.getAllRooms();
	}

	// build get Room by id REST API
	@GetMapping("/getRoom/{roomId}")
	public ResponseEntity<RoomEntity> getRoomById(@PathVariable(value = "roomId") Long RoomId) {
		return new ResponseEntity<RoomEntity>(roomService.getRoomById(RoomId), HttpStatus.OK);
	}

	// build update User REST API
	@PutMapping("/updateRoom/{roomId}")
	public ResponseEntity<RoomEntity> updateRoom(@PathVariable("roomId") Long RoomId, @RequestBody RoomEntity room) {
		return new ResponseEntity<RoomEntity>(roomService.updateRoom(room, RoomId), HttpStatus.OK);
	}
	
	//build update Room REST API
	@DeleteMapping("/deleteRoom/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable(value = "roomId") Long RoomId){
		roomService.deleteRoom(RoomId);
		return new ResponseEntity<String>("Room deleted successfully", HttpStatus.OK);
	}
	
}
