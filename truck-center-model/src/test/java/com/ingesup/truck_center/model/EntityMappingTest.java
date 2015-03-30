package com.ingesup.truck_center.model;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by lopes_f on 3/27/2015.
 * <florian.lopes@outlook.com>
 */
public class EntityMappingTest {

	private static final String PERSISTENCE_UNIT_NAME = "truckCenterPU";
	private EntityManagerFactory entityManagerFactory;

	@Test
	public void testJpaMapping() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		this.entityManagerFactory.createEntityManager();
	}
}
