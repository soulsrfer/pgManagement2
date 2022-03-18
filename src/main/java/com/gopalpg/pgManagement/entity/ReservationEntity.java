package com.gopalpg.pgManagement.entity;

import java.sql.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_reservation")
@Getter
@Setter
@JsonIgnoreProperties({ "hibernatelazyinitializer", "handled" })
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)

public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "payment")
	private int payment;

	@Column(name = "deposite")
	private int deposite;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date date_created;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date last_updated;

	@Column(name = "active")
	private boolean active;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "bed_id", nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private BedEntity bed;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private UserEntity user;

	@Override
	public String toString() {
		return "ReservationEntity [id=" + id + ", payment=" + payment + ", deposite=" + deposite + ", date_created="
				+ date_created + ", last_updated=" + last_updated + ", active=" + active + ", bed=" + bed + ", user="
				+ user + "]";
	}

	

}
