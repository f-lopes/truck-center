package com.ingesup.truckcenter.exception;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public class DriverNotFoundException extends Exception {

	public DriverNotFoundException() {
		super();
	}

	public DriverNotFoundException(String message) {
		super(message);
	}

	public DriverNotFoundException(Integer driverId) {
		super(String.format("Driver not found : %d", driverId));
	}
}
