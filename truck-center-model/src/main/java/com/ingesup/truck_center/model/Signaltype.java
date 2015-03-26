package com.ingesup.truck_center.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "SignalType")
public class Signaltype {

    @Id
    @GeneratedValue
    private int id;

    private String Label;

    public Signaltype(String label) {
        Label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
