package com.gopalpg.pgManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.AdminRepository;
import com.gopalpg.pgManagement.entity.AdminEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public AdminEntity saveAdmin(AdminEntity admin) {

		return adminRepository.save(admin);
	}

	@Override
	public List<AdminEntity> getAllAdmin() {

		return adminRepository.findAll();
	}

	@Override
	public AdminEntity getAdminbyId(Long id) {
		Optional<AdminEntity> admin = adminRepository.findById(id);
		if (admin.isPresent()) {
			return admin.get();
		} else {
			throw new ResourceNotFoundException("AdminEntity", "Id", id);
		}
	}

	@Override
	public AdminEntity updateAdmin(AdminEntity admin, Long id) {
		AdminEntity existingAdmin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("AdminEntity", "Id", id));
		existingAdmin.setUsername(admin.getUsername());
		existingAdmin.setPassword(admin.getPassword());
		
		//save existing admin in DB
		adminRepository.save(existingAdmin);
		
		return existingAdmin;
	}

	@Override
	public void deleteAdmin(long id) {

		// check whether a admin exist in DB or not
		
		adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AdminEntity", "Id", id));
		adminRepository.deleteById(id);
	}

}


















