package com.ingesup.truck_center.model;

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

	private Integer driverId;

	private Date date;

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
