package com.gopalpg.pgManagement.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_bed")
@Getter
@Setter
@JsonIgnoreProperties({"hibernatelazyinitializer", "handled"})
@AllArgsConstructor
@NoArgsConstructor
public class BedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "active")
	private boolean active;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private RoomEntity room;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bed")
	private ReservationEntity reserveBed;

	@Override
	public String toString() {
		return "BedEntity [id=" + id + ", active=" + active + ", room=" + room + ", reserveBed=" + reserveBed + "]";
	}

	
	
	/*
	 * @OneToOne(mappedBy = "bed", cascade = CascadeType.ALL)
	 * 
	 * @PrimaryKeyJoinColumn private ReservationEntity reservation;
	 */

	


	
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "bed") private
	 * Set<ReservationEntity> bedReserve;
	 */
	

}
