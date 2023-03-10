package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.repositories.EventRepository;

@Service
class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepo;

	@Override
	public List<Event> allEvents() {
		return eventRepo.findAll();
	}

	@Override
	public Event getEvent(int eventId) {

		Event event = null;
		Optional<Event> eventOpt = eventRepo.findById(eventId);
		if (eventOpt.isPresent()) {
			event = eventOpt.get();
		}
		return event;

	}

	@Override
	public Event create(Event event) {

		return eventRepo.saveAndFlush(event);
	}

	@Override
	public boolean deleteById(int eventId) {
		eventRepo.deleteById(eventId);
		return eventRepo.existsById(eventId);
	}

	@Override
	public Event update(int eventId, Event event) {
		Event eventUpdated = getEvent(eventId);

		eventUpdated.setName(event.getName());
		eventUpdated.setDescription(event.getDescription());
		eventUpdated.setLocation(event.getLocation());
		eventUpdated.setDate(event.getDate());

		return eventRepo.save(eventUpdated);
	}

}
