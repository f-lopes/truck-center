package com.ingesup.truckcenter.domain;

import java.util.Date;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public class AlertDTO {

	private Date date;

	private String driverId;

	public AlertDTO(Date date, String driverId) {

		this.date = date;
		this.driverId = driverId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
