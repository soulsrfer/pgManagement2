package com.gopalpg.pgManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.BedRepository;
import com.gopalpg.pgManagement.dao.ReservationRepository;
import com.gopalpg.pgManagement.dao.UserRepository;
import com.gopalpg.pgManagement.entity.BedEntity;
import com.gopalpg.pgManagement.entity.ReservationEntity;
import com.gopalpg.pgManagement.entity.UserEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private ReservationRepository reservationRepository;
	private UserRepository userRepository;
	private BedRepository bedRepository;

	public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository,
			BedRepository bedRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.userRepository = userRepository;
		this.bedRepository = bedRepository;
	}

	@Override
	public ReservationEntity saveReservation(ReservationEntity reservation, Long id) {
		UserEntity user = new UserEntity();
		List<ReservationEntity> reservationList = new ArrayList<ReservationEntity>();
		if (id != null) {
			user = userRepository.getById(id);
			reservation.setUser(user);
			reservationRepository.saveAll(reservationList);
			user.setReserveUser(reservationList);
		}

		return reservationRepository.save(reservation);

	}

	@Override
	public List<ReservationEntity> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public ReservationEntity getReservationById(Long id) {
		Optional<ReservationEntity> reservation = reservationRepository.findById(id);
		if (reservation.isPresent()) {
			return reservation.get();
		} else {
			throw new ResourceNotFoundException("ReservationEntity", "id", id);
		}
	}

	@Override
	public ReservationEntity updateReservation(ReservationEntity reservation, Long id) {
		// check whether user with given id is exist in DB or not
		ReservationEntity existingReservation = reservationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ReservationEntity", "Id", id));
		existingReservation.setActive(reservation.isActive());
		existingReservation.setDate_created(reservation.getDate_created());
		existingReservation.setLast_updated(reservation.getLast_updated());
		existingReservation.setPayment(reservation.getPayment());
		existingReservation.setDeposite(reservation.getDeposite());

		reservationRepository.save(existingReservation);
		return existingReservation;
	}

	@Override
	public void deleteReservation(long id) {
		// check whether a Bed exist in DB or not
		reservationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ReservationEntity", "Id", id));
		reservationRepository.deleteById(id);
	}

	// assign bed to reservation
	@Override
	public ReservationEntity reserveBed(Long reserveId, Long bedId) {
		ReservationEntity existingReservation = reservationRepository.findById(reserveId)
				.orElseThrow(() -> new ResourceNotFoundException("ReservationEntity", "Id", reserveId));
		BedEntity bed = new BedEntity();
		if (bedId != null) {
			bed = bedRepository.getById(bedId);
			existingReservation.setBed(bed);
			bed.setReserveBed(existingReservation);
			reservationRepository.save(existingReservation);

		}

		return existingReservation;
	}

}
