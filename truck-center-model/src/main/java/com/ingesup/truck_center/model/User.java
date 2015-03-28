package com.ingesup.truck_center.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 3/26/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table (name = "user")
@Inheritance (strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

	protected String firstName;

	protected String lastName;

	@Column(unique = true)
	protected String email;

	protected String password;

	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId")
	)
	private Set<Role> roles = new HashSet<Role>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Set<String> getRolesAsString() {
		final Set<String> rolesAsString = new HashSet<String>();
		if (this.roles != null) {
			for (Role role : this.roles) {
				rolesAsString.add(role.getName());
			}
		}

		return rolesAsString;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
