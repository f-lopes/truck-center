package com.ingesup.truckcenter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "DriverMessage")
public class DriverMessage extends BaseEntity {

//    @ManyToOne
//    @JoinColumn(name="driver_id")
    @ManyToOne
    private Driver driver;

    @ManyToOne
//    @JoinColumn(name="signal_type_id", nullable=false, updatable=false)
    private Signaltype signaltype;

    private String message;

    public DriverMessage() {
    }

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

