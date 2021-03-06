package com.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tbResponsible")
@NamedQueries({ @NamedQuery(name = "Responsible.getAllResponsible", query = "SELECT res FROM Responsible res") })
public class Responsible implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_activity")
	private Activity activity;

	@Column
	private String address;

	@Column
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idResponsible")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_job")
	private Job job;

	@Column
	private String lastName;

	@Column
	private String name;

	@Column
	private String phone;

	@ManyToOne
	@JoinColumn(name = "id_stateResponsible")
	private StateResponsible state;

	public Responsible() {
		super();
	}

	public Responsible(Activity activity, String address, String email, Integer id, String lastName, String name,
	        String phone, StateResponsible state) {
		super();
		this.activity = activity;
		this.address = address;
		this.email = email;
		this.id = id;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.state = state;
	}

	public Responsible(String address, String email, Integer id, String lastName, String name, String phone) {
		super();
		this.address = address;
		this.email = email;
		this.id = id;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getId() {
		return this.id;
	}

	public Job getJob() {
		return this.job;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public StateResponsible getState() {
		return this.state;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setState(StateResponsible state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Responsible [address=" + this.address + ", email=" + this.email + ", id=" + this.id + ", job="
		        + this.job + ", lastName=" + this.lastName + ", name=" + this.name + ", phone=" + this.phone
		        + ", state=" + this.state + "]";
	}
}
