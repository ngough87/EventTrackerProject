package com.skilldistillery.theomaha.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	Event event;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAthOmaha");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	
	@BeforeEach
	void setUp() throws Exception {
	    em = emf.createEntityManager();
	    event =em.find(Event.class,1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    event=null;
	}

	@Test
	void test_Event_mappings() {
	assertNotNull(event);
	assertEquals("Casa Madrigal" , event.getName());

	}
}
