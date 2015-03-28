package com.ingesup.truck_center.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lopes_f on 3/28/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	@Column (name = "name", length = 20)
	private String name;

	public Role() {

	}

	public Role(RoleEnum roleEnum){
		this.name = roleEnum.name();
	}

	public Role(String roleName) {
		this.name = roleName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Role)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Role role = (Role) o;

		if (name != null ? !name.equals(role.name) : role.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
