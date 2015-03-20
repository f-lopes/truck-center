package com.ingesup.truck_center.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lopes_f on 3/20/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "Truck")
public class Truck {

	@Id
	@GeneratedValue
	private String id;
}
