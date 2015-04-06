package com.ingesup.truckcenter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table (name = "Alert")
public class Alert extends BaseEntity {

	private Date date;

	@ManyToOne
	private Driver driver;

	public Alert() {
	}

	public Alert(Date date, Driver driver) {
		this.date = date;
		this.driver = driver;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
