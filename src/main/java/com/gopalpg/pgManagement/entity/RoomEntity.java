package com.gopalpg.pgManagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_room")
@Getter
@Setter
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "capacity")
	private int capacity;

	@Column(name = "rent")
	private int rent;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
	private List<BedEntity> beds;

	@Override
	public String toString() {
		return "RoomEntity [id=" + id + ", capacity=" + capacity + ", rent=" + rent + ", beds=" + beds + "]";
	}

}
