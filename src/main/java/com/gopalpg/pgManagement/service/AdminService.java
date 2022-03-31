package com.gopalpg.pgManagement.service;

import java.util.List;

import com.gopalpg.pgManagement.entity.AdminEntity;

public interface AdminService {

	AdminEntity saveAdmin(AdminEntity admin);
	
	List<AdminEntity> getAllAdmin();
	
	AdminEntity getAdminbyId(Long id);
	
	AdminEntity updateAdmin(AdminEntity admin, Long id);
	
	void deleteAdmin(long id);
}
