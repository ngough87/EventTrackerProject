package com.skilldistillery.theomaha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.theomaha.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
