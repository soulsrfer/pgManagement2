package com.gopalpg.pgManagement.service;

import java.util.List;

import com.gopalpg.pgManagement.entity.RoomEntity;

public interface RoomService {

	RoomEntity saveRoom(RoomEntity room);
	List<RoomEntity> getAllRooms();
	RoomEntity getRoomById(Long id);
	RoomEntity updateRoom(RoomEntity room, Long id);
	void deleteRoom(long id);
}
