package com.gopalpg.pgManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.RoomRepository;
import com.gopalpg.pgManagement.entity.RoomEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	private RoomRepository roomRepository;

	public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	@Override
	public RoomEntity saveRoom(RoomEntity room) {

		return roomRepository.save(room);
	}

	@Override
	public List<RoomEntity> getAllRooms() {
		return roomRepository.findAll();
	}

	@Override
	public RoomEntity getRoomById(Long id) {
		Optional<RoomEntity> room = roomRepository.findById(id);
		if (room.isPresent()) {
			return room.get();
		} else {
			throw new ResourceNotFoundException("RoomEntity", "Id", id);
		}

	}

	@Override
	public RoomEntity updateRoom(RoomEntity room, Long id) {

		// check whether room with given id is exist in DB or not
		RoomEntity existingRoom = roomRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("RoomEntity", "Id", id));
		existingRoom.setCapacity(room.getCapacity());
		existingRoom.setRent(room.getRent());

		roomRepository.save(existingRoom);
		return existingRoom;
	}

	@Override
	public void deleteRoom(long id) {
		// check whether a user exist in DB or not
		roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RoomEntity", "Id", id));
		roomRepository.deleteById(id);
	}
}
