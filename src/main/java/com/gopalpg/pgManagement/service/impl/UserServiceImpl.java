package com.gopalpg.pgManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.UserRepository;
import com.gopalpg.pgManagement.entity.UserEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserEntity saveUser(UserEntity user) {

		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserById(Long id) {
		Optional<UserEntity> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new ResourceNotFoundException("UserEntity", "Id", id);
		}
	}

	@Override
	public UserEntity updateUser(UserEntity user, Long id) {

		// check whether user with given id is exist in DB or not
		UserEntity existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", id));
		existingUser.setFirst_name(user.getFirst_name());
		existingUser.setLast_name(user.getLast_name());
		existingUser.setPhone(user.getPhone());
		existingUser.setPassword(user.getPassword());

		// save existing user in DB
		userRepository.save(existingUser);

		return existingUser;
	}

	@Override
	public void deleteUser(long id) {

		// check whether a user exist in DB or not
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", id));
		userRepository.deleteById(id);
		
	}

}
