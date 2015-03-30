package com.ingesup.truck.form;

import com.ingesup.truck_center.model.Truck;

import java.util.Date;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
public class AddTruckForm {

	private String identificationNumber;

	private int driverId;

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public Truck getTruck() {
		return new Truck(identificationNumber, new Date());
	}
}
