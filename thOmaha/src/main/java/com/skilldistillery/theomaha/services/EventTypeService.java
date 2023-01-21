package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.entities.EventType;

public interface EventTypeService  {
	
	List<EventType> allEventTypes();
	EventType  getEventType(int eventTypeId);
	EventType create(EventType eventType);
	EventType update(int eventTypeId, EventType eventType);
	boolean deleteById(int eventTypeId);
	

}
