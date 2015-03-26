package com.ingesup.truck_center.model;

import javax.persistence.*;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "SentMessage")
public class SentMessage {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private DriverMessage driverMessage;

    public SentMessage(Driver driver, DriverMessage driverMessage) {
        this.driver = driver;
        this.driverMessage = driverMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
