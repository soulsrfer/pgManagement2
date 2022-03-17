package com.gopalpg.pgManagement.service;

import java.util.List;

import com.gopalpg.pgManagement.entity.ProfileEntity;

public interface ProfileService {

	ProfileEntity saveProfile(ProfileEntity profile, Long id);
	List<ProfileEntity> getAllProfiles();
	ProfileEntity getProfileById(Long id);
	ProfileEntity updateProfile(ProfileEntity profile, Long id);
	void deleteProfile(long id);
}
