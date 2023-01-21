package com.skilldistillery.theomaha.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="rating")
	private List<Restaraunt> retaurants;
	
	@JsonIgnore
	@OneToMany(mappedBy="rating")
	private List<Event> events;
	
	

	public Rating() {
		super();
	}

	public Rating(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

	public List<Restaraunt> getRetaurants() {
		return retaurants;
	}

	public void setRetaurants(List<Restaraunt> retaurants) {
		this.retaurants = retaurants;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", name=" + name + ", description=" + description + ", retaurants=" + retaurants
				+ ", events=" + events + "]";
	}
	

}