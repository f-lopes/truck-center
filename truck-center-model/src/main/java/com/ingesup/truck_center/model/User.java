package com.ingesup.truck_center.model;

import javax.persistence.*;

/**
 * Created by lopes_f on 3/26/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table (name = "user")
@Inheritance (strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	protected Integer id;

	protected String firstName;

	protected String lastName;

	@Column(unique = true)
	protected String email;

	protected String password;
}
