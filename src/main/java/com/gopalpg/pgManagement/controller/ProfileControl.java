package com.gopalpg.pgManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopalpg.pgManagement.entity.ProfileEntity;
import com.gopalpg.pgManagement.service.ProfileService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProfileControl {

	private ProfileService profileService;

	public ProfileControl(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	// build create Profile REST API

	@PostMapping(value = "/createProfile/{userId}")
	public ResponseEntity<ProfileEntity> saveProfile(@PathVariable(value = "userId") Long Profileid, @RequestBody ProfileEntity profile) {
		return new ResponseEntity<ProfileEntity>(profileService.saveProfile(profile, Profileid), HttpStatus.CREATED);
	}
	
	//build get all Profiles REST API
	@GetMapping("/getAllProfiles")
	public List<ProfileEntity> getAllProfiles(){
		return profileService.getAllProfiles();
	}
	
	//build get Profile by id REST API
	@GetMapping("/getProfile/{profileId}")
	public ResponseEntity<ProfileEntity> getProfileById(@PathVariable(value = "profileId") Long ProfileId){
		return new ResponseEntity<ProfileEntity>(profileService.getProfileById(ProfileId),HttpStatus.OK);
	}
	//build update Profile REST API
	@PutMapping("/updateProfile/{profileId}")
	public ResponseEntity<ProfileEntity> updateProfile(@PathVariable(value = "profileId") Long ProfileId, @RequestBody ProfileEntity profile){
		return new ResponseEntity<ProfileEntity>(profileService.updateProfile(profile, ProfileId), HttpStatus.OK);
	}
	
	//build delete Profile REST API
	@DeleteMapping("/deleteProfile/{profileId}")
	public ResponseEntity<String> deleteProfile(@PathVariable(value = "profileId") Long UserId){
		profileService.deleteProfile(UserId);
		return new ResponseEntity<String>("Profile deleted successfully", HttpStatus.OK);
	}
}
