package com.ingesup.truck_center.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lopes_f on 3/20/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "Truck")
public class Truck {

	@Id
	@GeneratedValue
	private Integer id;

    private String identificationNumber;

    private Date arrivalDate;

	public Truck() {
	}

	public Truck(String identificationNumber, Date arrivalDate) {
        this.identificationNumber = identificationNumber;
        this.arrivalDate = arrivalDate;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
}
