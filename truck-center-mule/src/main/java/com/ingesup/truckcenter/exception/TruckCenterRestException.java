package com.ingesup.truckcenter.exception;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public class TruckCenterRestException extends Exception {

	public TruckCenterRestException() {
		super();
	}

	public TruckCenterRestException(String message) {
		super(message);
	}

	public TruckCenterRestException(String message, Throwable cause) {
		super(message, cause);
	}
}
