package com.ingesup.truckcenter.domain;

import java.util.Date;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public class AlertDTO {

	private Date date;

	private int driverId;

	public AlertDTO(Date date, int driverId) {
		this.date = date;
		this.driverId = driverId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
}
