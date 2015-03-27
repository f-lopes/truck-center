package com.ingesup.truck_center.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by lopes_f on 3/26/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table (name = "admin")
@PrimaryKeyJoinColumn (name = "admin_id", referencedColumnName = "id")
public class Admin extends User {
}
