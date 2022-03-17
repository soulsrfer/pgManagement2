package com.gopalpg.pgManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_profile")
@Getter
@Setter
@JsonIgnoreProperties({ "hibernatelazyinitializer", "handled" })
public class ProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "middle_name")
	private String middle_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private boolean active;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private UserEntity user;

	@Override
	public String toString() {
		return "ProfileEntity [id=" + id + ", first_name=" + first_name + ", middle_name=" + middle_name
				+ ", last_name=" + last_name + ", phone=" + phone + ", email=" + email + ", active=" + active
				+ ", user=" + user + "]";
	}

}
