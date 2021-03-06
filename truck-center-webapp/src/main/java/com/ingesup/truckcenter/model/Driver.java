package com.ingesup.truckcenter.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table(name = "Driver")
@PrimaryKeyJoinColumn (name = "driver_id", referencedColumnName = "id")
public class Driver extends User {

    @OneToOne
    @JoinColumn(name="truck_id", nullable=false, updatable=false)
    private Truck truck;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "driver")
    private Set<DriverMessage> messages;

	public Driver() {
    }

    public Driver(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Set<DriverMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<DriverMessage> messages) {
        this.messages = messages;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
