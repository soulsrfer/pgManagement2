package com.gopalpg.pgManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gopalpg.pgManagement.dao.BedRepository;
import com.gopalpg.pgManagement.dao.RoomRepository;
import com.gopalpg.pgManagement.entity.BedEntity;
import com.gopalpg.pgManagement.entity.RoomEntity;
import com.gopalpg.pgManagement.exception.ResourceNotFoundException;
import com.gopalpg.pgManagement.service.BedService;

@Service
public class BedServiceImpl implements BedService{

	private BedRepository bedRepository;
	private RoomRepository roomRepository;

	

	public BedServiceImpl(BedRepository bedRepository, RoomRepository roomRepository) {
		super();
		this.bedRepository = bedRepository;
		this.roomRepository = roomRepository;
	}



	@Override
	public BedEntity saveBed(BedEntity bed, Long id) {
		RoomEntity room = new RoomEntity();
		List<BedEntity> bedList = new ArrayList<BedEntity>();
		if(id != null) {
			room = roomRepository.getById(id);
			bed.setRoom(room);
			bedList.add(bed);
			bedRepository.saveAll(bedList);
			room.setBeds(bedList);
		}
		return bedRepository.save(bed);
	}



	@Override
	public List<BedEntity> getAllBeds() {
		return bedRepository.findAll();
	}



	@Override
	public BedEntity getBedById(Long id) {
		Optional<BedEntity> bed = bedRepository.findById(id);
		if(bed.isPresent()) {
			return bed.get();
		}else {
			throw new ResourceNotFoundException("BedEntity", "Id", id);
		}
	}



	@Override
	public BedEntity updateBed(BedEntity bed, Long id) {
		
		// check whether bed with given id is exist in DB or not
		BedEntity existingBed = bedRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("BedEntity", "Id", id));
		existingBed.setActive(bed.isActive());
		
		// save existing bed in DB
		bedRepository.save(existingBed);
		
		return existingBed;
	}



	@Override
	public void deleteBed(long id) {
		//check whether a Bed exist in DB or not
		bedRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BedEntity", "Id", id));
		bedRepository.deleteById(id);
	}
	
	
}
