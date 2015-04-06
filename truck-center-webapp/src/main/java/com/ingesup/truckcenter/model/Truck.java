package com.ingesup.truckcenter.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by lopes_f on 3/20/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "Truck")
public class Truck extends BaseEntity {

    private String identificationNumber;

    @OneToOne
    @JoinColumn (name="driver_id", nullable=true, updatable=true)
    private Driver driver;

	public Truck() {
	}

	public Truck(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

	public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
