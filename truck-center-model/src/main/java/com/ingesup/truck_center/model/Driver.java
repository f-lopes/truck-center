package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table(name = "Driver")
@PrimaryKeyJoinColumn (name = "driver_id", referencedColumnName = "id")
public class Driver extends User {

    private Date arrivalDate;

    @OneToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Truck truck;

	public Driver() {
	}

	public Driver(String mail, String password, Date arrivalDate, Truck truck) {
        this.email = mail;
        this.password = password;
        this.arrivalDate = arrivalDate;
        this.truck = truck;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
