package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Maxime on 21/03/2015.
 */
public class Driver {

    @Id
    @GeneratedValue
    private int id;


    private String mail;

    private String password;

    private Date arrivalDate;

    @OneToOne
    @JoinColumn(name="id", nullable=false, updatable=false)
    private Truck truck;

    public Driver(String mail, String password, Date arrivalDate, Truck truck) {
        this.mail = mail;
        this.password = password;
        this.arrivalDate = arrivalDate;
        this.truck = truck;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
