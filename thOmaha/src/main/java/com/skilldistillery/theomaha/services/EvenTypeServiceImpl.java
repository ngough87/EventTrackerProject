package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.entities.EventType;
import com.skilldistillery.theomaha.repositories.EvenTypeRepository;

@Service
public class EvenTypeServiceImpl implements EventTypeService {

	@Autowired
	private EvenTypeRepository eventTypeRepo;

	@Override
	public List<EventType> allEventTypes() {
		return eventTypeRepo.findAll();
	}

	@Override
	public EventType getEventType(int eventTypeId) {
		EventType eventType = null;
		Optional<EventType> eventTypeOpt = eventTypeRepo.findById(eventTypeId);
		if (eventTypeOpt.isPresent()) {
			eventType = eventTypeOpt.get();
		}
		return eventType;
	}

	@Override
	public EventType create(EventType eventType) {
		return eventTypeRepo.saveAndFlush(eventType);
	}

	@Override
	public EventType update(int eventTypeId, EventType eventType) {
		EventType eventTypeUpdated = getEventType(eventTypeId);

		eventTypeUpdated.setName(eventType.getName());
		eventTypeUpdated.setDescription((eventType.getDescription()));

		return eventTypeRepo.save(eventTypeUpdated);
		
	}

	@Override
	public boolean deleteById(int eventTypeId) {
		eventTypeRepo.deleteById(eventTypeId);
		return eventTypeRepo.existsById(eventTypeId);
	}

	
	
}
