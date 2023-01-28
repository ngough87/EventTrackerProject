console.log("script.js loaded");

window.addEventListener("load", function (e) {
  console.log("Page is loaded");
  init();
});

function init() {
  document.eventForm.lookup.addEventListener("click", function (evt) {
    evt.preventDefault();
    let eventId = document.eventForm.eventId.value;
    if (!isNaN(eventId) && eventId > 0) {
      getEvent(eventId);
    }
  });
 

  document.newEventForm.addEventButton.addEventListener('click', function(e) {
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

	
	console.log(newEvent)
	addEvent(newEvent);
});

}

function addEvent(newEvent) {
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

function loadEvents() {
  //AJAX

  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/events");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let eventList = JSON.parse(xhr.responseText);
        console.log(eventList);
        displayEvents(eventList);
      } else {
        //TODO - display error somewhere?
      }
    }
  };
  xhr.send();
}

function displayEvents(eventList) {
  //DOM
  // let tbody =  document.getElementById('eventsList').firstElementChild.nextElementSibling;
  // console.log(tbody);
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

function displayEvent(event) {
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

  console.log(event);
}

function displayError(message) {
	let div = document.getElementById('eventDetails');
	div.textContent = message;
	
}




