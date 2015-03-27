package com.ingesup.truck_center.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Maxime on 20/03/2015.
 */
@Entity
@Table(name = "TruckState")
public class TruckState extends BaseEntity {

	public TruckState() {
	}
}
