package com.ingesup.truckcenter.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Maxime on 21/03/2015.
 */
@Entity
@Table (name = "SignalType")
public class Signaltype extends BaseEntity {

    private String Label;

    public Signaltype() {
    }

    public Signaltype(String label) {
        Label = label;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
