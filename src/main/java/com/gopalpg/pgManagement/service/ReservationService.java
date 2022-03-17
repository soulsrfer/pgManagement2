package com.gopalpg.pgManagement.service;

import java.util.List;

import com.gopalpg.pgManagement.entity.ReservationEntity;

public interface ReservationService {

	ReservationEntity saveReservation(ReservationEntity reservation, Long id);
	List<ReservationEntity> getAllReservations();
	ReservationEntity getReservationById(Long id);
	ReservationEntity updateReservation(ReservationEntity reservation, Long id);
	void deleteReservation(long id);
	ReservationEntity reserveBed(Long reserveId, Long bedId);
	
}
