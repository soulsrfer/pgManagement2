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

import com.gopalpg.pgManagement.entity.AdminEntity;
import com.gopalpg.pgManagement.service.AdminService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AdminController {

	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	// build create Admin REST API

	@PostMapping("/createAdmin")
	public ResponseEntity<AdminEntity> saveUser(@RequestBody AdminEntity admin) {
		return new ResponseEntity<AdminEntity>(adminService.saveAdmin(admin), HttpStatus.CREATED);
	}

	// build get all user REST API
	@GetMapping("/getAllAdmin")
	public List<AdminEntity> getAllAdmin() {
		return adminService.getAllAdmin();
	}
	
	//build get user by id REST API
		@GetMapping("/getAdmin/{adminId}")
		public ResponseEntity<AdminEntity> getAdminById(@PathVariable(value = "adminId") Long AdminId){
			return new ResponseEntity<AdminEntity>(adminService.getAdminbyId(AdminId),HttpStatus.OK);
		}
		
		//build update admin REST API
		@PutMapping("/updateAdmin/{adminId}")
		public ResponseEntity<AdminEntity> updateAdmin(@PathVariable("adminId") Long AdminId, @RequestBody AdminEntity admin){
			return new ResponseEntity<AdminEntity>(adminService.updateAdmin(admin, AdminId), HttpStatus.OK);
		}
		//build delete User REST API
		
		@DeleteMapping("/deleteAdmin/{adminId}")
		public ResponseEntity<String> deleteAdmin(@PathVariable(value = "adminId") Long AdminId){
			adminService.deleteAdmin(AdminId);
			return new ResponseEntity<String>("Admin deleted successfully", HttpStatus.OK);
		}

}
