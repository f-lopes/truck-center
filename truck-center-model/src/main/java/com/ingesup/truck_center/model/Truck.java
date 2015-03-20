package com.ingesup.truck_center.model;

import javax.persistence.*;
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
	private int id;

    private String identificationNumber;

    private Date arrivalDate;

    @OneToMany
    @JoinColumn(name="truckState_id", nullable=false, updatable=false)
    private TruckTruckState truckTruckState;


    public Truck(String identificationNumber, Date arrivalDate, TruckTruckState truckTruckState) {
        this.identificationNumber = identificationNumber;
        this.arrivalDate = arrivalDate;
        this.truckTruckState = truckTruckState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public TruckTruckState getTruckState() {
        return truckTruckState;
    }

    public void setTruckState(TruckTruckState truckState) {
        this.truckTruckState = truckTruckState;
    }
}
