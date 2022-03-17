package com.gopalpg.pgManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.ProfileRepository;
import com.gopalpg.pgManagement.dao.UserRepository;
import com.gopalpg.pgManagement.entity.ProfileEntity;
import com.gopalpg.pgManagement.entity.UserEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	private ProfileRepository profileRepository;
	private UserRepository userRepository;

	public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
		super();
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ProfileEntity saveProfile(ProfileEntity profile, Long id) {
		UserEntity user = new UserEntity();
		if (id != null) {
			user = userRepository.getById(id);
			profile.setUser(user);
			user.setUserProfile(profile);

		}
		return profileRepository.save(profile);
	}

	@Override
	public List<ProfileEntity> getAllProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public ProfileEntity getProfileById(Long id) {
		Optional<ProfileEntity> profile = profileRepository.findById(id);
		if (profile.isPresent()) {
			return profile.get();
		} else {
			throw new ResourceNotFoundException("ProfileEntity", "id", id);
		}
	}

	@Override
	public ProfileEntity updateProfile(ProfileEntity profile, Long id) {
		// check whether user with given id is exist in DB or not
		ProfileEntity existingProfile = profileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ProfileEntity", "Id", id));
		existingProfile.setActive(profile.isActive());
		existingProfile.setFirst_name(profile.getFirst_name());
		existingProfile.setMiddle_name(profile.getMiddle_name());
		existingProfile.setLast_name(profile.getLast_name());
		existingProfile.setEmail(profile.getEmail());
		existingProfile.setPhone(profile.getPhone());
		
		profileRepository.save(existingProfile);
		
		return existingProfile;
	}

	@Override
	public void deleteProfile(long id) {
		//check whether a Profile exist in DB or not
		profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProfileEntity", "Id", id));
		profileRepository.deleteById(id);
	}

}
