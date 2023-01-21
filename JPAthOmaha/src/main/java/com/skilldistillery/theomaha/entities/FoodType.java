package com.skilldistillery.theomaha.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="food_type")
public class FoodType {

	

		@Id
		
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String name;
		
		private String description;
		
		@JsonIgnore
		@OneToMany(mappedBy="foodType")
		private List<Restaraunt> restaraunts;

		public FoodType() {
			super();
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

		

		public List<Restaraunt> getRestaraunts() {
			return restaraunts;
		}

		public void setRestaraunts(List<Restaraunt> restaraunts) {
			this.restaraunts = restaraunts;
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
			FoodType other = (FoodType) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "FoodType [id=" + id + ", name=" + name + ", description=" + description + ", restaraunts="
					+ restaraunts + "]";
		}
		
		
}
