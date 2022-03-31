package com.gopalpg.pgManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gopalpg.pgManagement.entity.BedEntity;

@Repository
public interface BedRepository extends JpaRepository<BedEntity, Long> {

}
