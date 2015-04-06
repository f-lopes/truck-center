package com.ingesup.truckcenter.form;

import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.model.Truck;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
public class AddDriverForm {

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Truck truck;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Driver getDriverFromForm() {
		final Driver driver = new Driver(firstName, lastName, email, password);
		driver.setTruck(truck);

		return driver;
	}
}
