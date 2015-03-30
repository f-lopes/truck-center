package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lopes_f on 3/20/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "Truck")
public class Truck extends BaseEntity {

    private String identificationNumber;

    private Date arrivalDate;

    @ManyToOne
    private Driver driver;

	public Truck() {
	}

	public Truck(String identificationNumber, Date arrivalDate) {
        this.identificationNumber = identificationNumber;
        this.arrivalDate = arrivalDate;
    }

	public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
