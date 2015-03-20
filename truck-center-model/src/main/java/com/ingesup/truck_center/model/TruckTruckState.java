package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 20/03/2015.
 */
@Entity
@Table(name = "Truck_TruckState")
public class TruckTruckState {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Truck truck;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private TruckState truckState;

    private Date dateEvent;

    public TruckTruckState(Truck truck, TruckState truckState, Date dateEvent) {
        this.truck = truck;
        this.truckState = truckState;
        this.dateEvent = dateEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public TruckState getTruckState() {
        return truckState;
    }

    public void setTruckState(TruckState truckState) {
        this.truckState = truckState;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }
}
