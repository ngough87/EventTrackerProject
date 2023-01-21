package com.skilldistillery.theomaha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.entities.EventType;

public interface EvenTypeRepository extends JpaRepository<EventType, Integer> {

}
