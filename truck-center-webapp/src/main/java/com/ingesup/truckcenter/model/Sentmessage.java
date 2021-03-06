package com.ingesup.truckcenter.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "SentMessage")
public class Sentmessage extends BaseEntity {

    @ManyToOne
//    @JoinColumn(name="driver_id", nullable=false, updatable=false)
    private Driver driver;

    @ManyToOne
//    @JoinColumn(name="driver_message_id", nullable=false, updatable=false)
    private DriverMessage driverMessage;

    public Sentmessage() {
    }

    public Sentmessage(Driver driver, DriverMessage driverMessage) {
        this.driver = driver;
        this.driverMessage = driverMessage;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverMessage getDriverMessage() {
        return driverMessage;
    }

    public void setDriverMessage(DriverMessage driverMessage) {
        this.driverMessage = driverMessage;
    }
}
