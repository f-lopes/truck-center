package com.ingesup.truck_center.model;

import javax.persistence.*;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "DriverMessage")
public class DriverMessage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Driver driver;

    @OneToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Signaltype signaltype;


    private String message;

    public DriverMessage(Driver driver, Signaltype signaltype, String message) {
        this.driver = driver;
        this.signaltype = signaltype;
        this.message = message;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Signaltype getSignaltype() {
        return signaltype;
    }

    public void setSignaltype(Signaltype signaltype) {
        this.signaltype = signaltype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

