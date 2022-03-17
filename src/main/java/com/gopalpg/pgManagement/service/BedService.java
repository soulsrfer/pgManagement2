package com.gopalpg.pgManagement.service;

import java.util.List;

import com.gopalpg.pgManagement.entity.BedEntity;

public interface BedService {

	BedEntity saveBed(BedEntity bed,Long id);
	List<BedEntity> getAllBeds();
	BedEntity getBedById(Long id);
	BedEntity updateBed(BedEntity bed, Long id);
	void deleteBed(long id);
}
