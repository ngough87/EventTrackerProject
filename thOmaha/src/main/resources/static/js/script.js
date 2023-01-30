window.addEventListener("load", function (e) {
  console.log("Page is loaded");
  init();
});
//let eventList;
function init() {

  document.eventForm.lookup.addEventListener("click", function (evt) {
    evt.preventDefault();
    let eventId = document.eventForm.eventId.value;
    if (!isNaN(eventId) && eventId > 0) {
      getEvent(eventId);
    }
  });

  document.newEventForm.addEventButton.addEventListener("click", function(e){
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
	createEvent(newEvent);


  });

  document.eventLoadForm.lookupAll.addEventListener("click", function(evt){
	evt.preventDefault();
	loadEvents();


  });
}


function createEvent(event) {

	let xhr = new XMLHttpRequest();
  
	xhr.open("POST", "api/events");
	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body
	xhr.onreadystatechange = function () {
	  if (xhr.readyState === 4) {
		if (xhr.status === 200 || xhr.status === 201) {
		  let event = JSON.parse(xhr.responseText);
		  displayEvent(event);
		} else {
		  displayError("Error creating event: " + xhr.status);
		}
	  }
	};
	xhr.send(JSON.stringify(event));
  }

  function displayError(message) {
	let div = document.getElementById('eventDetails');
	div.textContent = message;

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
	let id = document.createElement("div");
	id.textContent = event.id;
	id.style.display="none";
	dataDiv.append(id);
  
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
  
	let deleteButton = document.createElement("input");
	deleteButton.type = "button";
	deleteButton.name = "delete";
	deleteButton.value = "Delete";
	deleteButton.addEventListener("click", deleteEventButton);
	dataDiv.appendChild(deleteButton);
  
	let edit = document.createElement("input");
	edit.type = "button";
	edit.name = "edit";
	edit.value = "Edit";
	edit.addEventListener("click", editEventForm);
	dataDiv.append(edit);
  }
  


function loadEvents() {
  //AJAX

  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/events");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let eventList = JSON.parse(xhr.responseText);

        displayEvents(eventList);
      } else {
        displayError("All events not found" + xhr.status);
      }
    }
  };

  xhr.send();
}

function displayEvents(eventList) {
  console.log(eventList);
  createTable(eventList);
}

let createTable = function (eventList) {
  let eventOutput = document.getElementById("output");
  eventOutput.textContent = "";
  let table = document.createElement("table");
  createHead(table);
  createBody(table, eventList);
  eventOutput.appendChild(table);
};

let createHead = function (table) {
  //create thead
  let headers = [
    "Name",
    "Description",
    "Location",
    "Date",
    "Event Type",
    "Category",
    "Rating"
  ];
  let thead = document.createElement("thead");
  let trow = document.createElement("tr");
  for (i = 0; i <= headers.length; i++) {
    let th = document.createElement("th");
    th.textContent = headers[i];
    trow.appendChild(th);
  }
  table.appendChild(thead);
  thead.appendChild(trow);
};
let createBody = function (table, eventList) {
  let tbody = document.createElement("tbody");

  eventList.forEach(function (eventList) {
    let trow = document.createElement("tr");

 
    let name = document.createElement("td");
    name.textContent = eventList.name;
    trow.appendChild(name);

  
    let description = document.createElement("td");
    description.textContent = eventList.description;
    trow.appendChild(description);

 
    let location = document.createElement("td");
    location.textContent = eventList.location;
    trow.appendChild(location);

 
    let date = document.createElement("td");
    date.textContent = eventList.date;
    trow.appendChild(date);

  
    let eventType = document.createElement("td");
    eventType.textContent = eventList.eventType["name"];
    trow.appendChild(eventType);

   
    let category = document.createElement("td");
    category.textContent = eventList.category["name"];
    trow.appendChild(category);
   
    let rating = document.createElement("td");
	rating.textContent = eventList.rating["name"]; 
    trow.appendChild(rating);

    tbody.appendChild(trow);
    table.appendChild(tbody);
  });
};


function idForUpdate(id) {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "api/events/" + id);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        let eventId = JSON.parse(xhr.responseText);
        addInfoToForm(eventId);
      } else {
        console.log(xhr.status);
      }
    }
  };
  xhr.send();
}
function editEventForm() {
 let div2 = document.getElementById("output");
	let div = document.getElementById("eventDetails");
  let id = div.firstElementChild.textContent;
  console.log(id);
  div.textContent = "";

  idForUpdate(id);
  let hiddenId = document.createElement("div");
  hiddenId.textContent = id;
  hiddenId.style.display = "none";
  console.log(id);

  let editEvent = document.createElement("form");
  let name = document.createElement("input");
  let description = document.createElement("input");
  let location = document.createElement("input");
  let date = document.createElement("input");
  let submit = document.createElement("button");
 
  editEvent.name = "editEvent";
  name.type = "text"; 
  name.name = "name";
  description.type = "text";
   description.name = "description";
  location.type = "text";
  location.name = "location";
  date.type = "date"; 
  date.name = "date";
  submit.name = "submit";
  submit.value = "Submit";
  submit.innerHTML = "Submit";

  editEvent.appendChild(hiddenId);
  console.log('after:' + id);
  editEvent.appendChild(name);
  editEvent.appendChild(description);
  editEvent.appendChild(location);
  editEvent.appendChild(date);
  div2.appendChild(editEvent);

  for (input of editEvent) {
	let br = document.createElement('br');
	input.after(br);
}
}

function addInfoToForm(eventId) {
  let editForm = document.editEvent;
  editForm.name.value = eventId.name;
  editForm.description.value = eventId.description;
  editForm.date.value = eventId.date;
  editForm.location.value = eventId.location;
  editForm.submit.addEventListener("click", addUpdatedEvent);
}

function addUpdatedEvent(e) {
  e.preventDefault();
  let updateForm = document.editEvent;
  let id = updateForm.firstElementChild.textContent;
  
  let updatedEvent = {
	  name: updateForm.name.value,
	  description: updateForm.description.value,
	  location: updateForm.location.value,
	  date: updateForm.date.value,
	};
	
	let xhr = new XMLHttpRequest();
  xhr.open("PUT", "api/events/" + id);

  xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON request body
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 200 || xhr.status === 201) {
        let event = JSON.parse(xhr.responseText);
        //displayEvent(event);
		alert(event);
      } else {
        displayError("Error updating event: " + xhr.status);
      }
    }
  };

  xhr.send(JSON.stringify(updatedEvent));
}

function deleteEventButton() {
  let id =
    document.getElementById("eventDetails").firstElementChild.textContent;
  console.log(id);
  deleteEvent(id);
}

function deleteEvent(id) {
  let xhr = new XMLHttpRequest();

  xhr.open("DELETE", "api/events/" + id);
  console.log("Id is: " + id);
  console.log(id);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      if (xhr.status === 204 || 200) {
        alert("This event has been deleted.");
      } else if (xhr.status === 404) {
        displayError("ERROR: Event not found.");
      }
    }
  };

  xhr.send();
}
