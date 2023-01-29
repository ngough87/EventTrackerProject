window.addEventListener("load", function (e) {
  console.log("Page is loaded");
  init();
});

function init() {
  loadEvents();
  document.eventForm.lookup.addEventListener("click", function (evt) {
    evt.preventDefault();
    let eventId = document.eventForm.eventId.value;
    if (!isNaN(eventId) && eventId > 0) {
      getEvent(eventId);
    }
  });

  document.newEventForm.addEventButton.addEventListener('click', createEvent);
}
function loadEvents() {
  //AJAX

  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/events");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let eventsList = JSON.parse(xhr.responseText);

        displayEvents(eventsList);
      } else {
        displayError("All events not found" + xhr.status);
      }
    }
  };

  xhr.send();
}

function displayEvents(eventsList) {
  let eventTable = document.getElementById("lookupAll");
  eventTable.textContent = "";
  let tableHeader = document.createElement("thead");
  let headerRow = document.createElement("tr");
  let eventHeader = document.createElement("th");
  let descriptionHeader = document.createElement("th");
  let locationHeader = document.createElement("th");
  let dateHeader = document.createElement("th");
  let eventTypeHeader = document.createElement("th");
  let categoryHeader = document.createElement("th");
  let ratingHeader = document.createElement("th");

  eventHeader.textContent = "Event Name";
  descriptionHeader.textContent = "Description";
  locationHeader.textContent = "Location";
  dateHeader.textContent = "Date";
  eventTypeHeader.textContent = "Event Type";
  categoryHeader.textContent = "Category";
  ratingHeader.textContent = "Price Rating";

  tableHeader.appendChild(headerRow);
  eventTable.appendChild(tableHeader);

  let tbody = document.createElement("tbody");
  eventTable.append(tbody);
  for (eventList of eventsList) {
    let tr = document.createElement("tr");
    let tdId = document.createElement("td");
    let tdName = document.createElement("td");
    let tdDescritpion = document.createElement("td");
    let tdLocation = document.createElement("td");
    let tdDate = document.createElement("td");
    let tdEventType = document.createElement("td");
    let tdCategory = document.createElement("td");
    let tdRating = document.createElement("td");

    tdId.textContent = eventList.id;
    tdName.textContent = eventList.name;
    tdDescritpion.textContent = eventList.description;
    tdLocation.textContent = eventList.location;
    tdDate.textContent = eventList.date;
    tdEventType.textContent = eventList.eventType;
    tdCategory.textContent = eventList.category;
    tdRating.textContent = eventList.rating;
  }
}

function displayEvent(event) {
  //   let dataDiv = document.getElementById("eventDetails");
  //   dataDiv.textContent = "";
  //   let id = document.createElement("div");
  //   id.textContent = event.id;
  //   dataDiv.append(id);
  //   let h1 = document.createElement("h1");
  //   h1.textContent = event.name;
  //   dataDiv.appendChilde(h1);
  //   let description = document.createElement("div");
  //   description.textContent = event.description;
  //   dataDiv.appendChild(description);
  //   let location = document.createElement('h4');
  //   location.textContent = event.location;
  //   dataDiv.appendChild(location);
  //   let date = document.createElement('h4');
  //   date.textContent = event.date;
  //   dataDiv.appendChild(date);
  //   let category = document.createElement('h4');
  //   category.textContent = category.category;
  //   dataDiv.appendChild(category);
  //   let eventType = document.createElement('h4');
  //   eventType.textContent = event.eventType;
  //   dataDiv.appendChild(eventType);
  //   let rating = document.createElement('h4');
  //   rating.textContent = event.rating;
  //   dataDiv.appendChild(rating);
  let dataDiv = document.getElementById("eventDetails");
  dataDiv.textContent = "";

  let h1 = document.createElement("h1");
  h1.textContent = event.name;
  dataDiv.appendChild(h1);
  let description = document.createElement("description");
  description.textContent = event.description;
  dataDiv.appendChild(description);
  let ul = document.createElement("ul");
  dataDiv.appendChild(ul);
  let id = document.createElement("li");
  id.textContent = "Event Id: " + event.id;
  ul.append(id);
  let li = document.createElement("li");
  li.textContent = "Location: " + event.location;
  ul.appendChild(li);
  li = document.createElement("li");
  li.textContent = "Date : " + event.date;
  ul.appendChild(li);
  li = document.createElement("li");
  li.textContent = "Event Type: " + event.eventType["name"];
  ul.appendChild(li);
  li = document.createElement("li");
  li.textContent = "Category: " + event.category["name"];
  ul.appendChild(li);
  li = document.createElement("li");
  li.textContent = "Price Rating: " + event.rating["name"];
  ul.appendChild(li);

  let deleteButton = document.createElement("input");
  deleteButton.type = "button";
  deleteButton.name = "delete";
  deleteButton.value = "Delete";
  deleteButton.addEventListener("click", deleteEventButton);
  div.appendChild(deleteButton);

  let edit = document.createElement("input");
  edit.type = "button";
  edit.name = "edit";
  edit.value = "Edit";
  edit.addEventListener("click", eventUpdateForm);
  div.append(edit);
}

function getEvent(eventId) {
  console.log(eventId);

  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/events/" + eventId);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let event = JSON.parse(xhr.responseText);
        console.log(event);
        displayEvent(event);
      } else {
        displayError("Event not found.");
      }
    }
  };
  xhr.send();
}
function createEvent(e) {
	e.preventDefault();
	let newEvent = {
		name: document.newEventForm.name.value,
		description: document.newEventForm.description.value,
		location: document.newEventForm.location.value,
		date: document.newEventForm.date.value,
		eventType:{id: document.newEventForm.eventType.value},
		category: {id: document.newEventForm.category.value},
		rating: {id: document.newEventForm.rating.value}
	};
	
	let xhr = new XMLHttpRequest();

	xhr.open('POST', 'api/events');
	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let event = JSON.parse(xhr.responseText);
				displayEvent(event);
			}
			else {
				displayError("Error creating event: " + xhr.status);
			}
		}
	};
	xhr.send(JSON.stringify(newEvent));
	}