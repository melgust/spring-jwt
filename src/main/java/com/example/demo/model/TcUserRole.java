package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tc_user_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
public class TcUserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userRoleId;

	@NotNull
	private byte statusId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated = new Date();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private TcUser tcUser;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private TcRole tcRole;

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public byte getStatusId() {
		return statusId;
	}

	public void setStatusId(byte statusId) {
		this.statusId = statusId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public TcUser getTcUser() {
		return tcUser;
	}

	public void setTcUser(TcUser tcUser) {
		this.tcUser = tcUser;
	}

	public TcRole getTcRole() {
		return tcRole;
	}

	public void setTcRole(TcRole tcRole) {
		this.tcRole = tcRole;
	}

}
