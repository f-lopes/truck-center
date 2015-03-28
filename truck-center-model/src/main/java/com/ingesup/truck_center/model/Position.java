package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 20/03/2015.
 */
@Entity
@Table (name = "Position")
public class Position extends BaseEntity {

    private double latitude;

    private double longitude;

    private Date positionDate;

    @ManyToOne
    private Truck truck;

    public Position(double latitude, double longitude, Date positionDate, Truck truck) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.positionDate = positionDate;
        this.truck = truck;
    }

    public Position() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
