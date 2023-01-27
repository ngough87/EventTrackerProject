console.log('script.js loaded');

window.addEventListener('load', function(e){
	console.log('Page is loaded');
	init();
	
	
});

function init(){
	loadEvents();
	
}

function loadEvents(){
	//AJAX
	
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/events');
	xhr.onreadystatechange = function(){
		if(xhr.readyState ===4) {
			if(xhr.status === 200){
			let eventList = JSON.parse(xhr.responseText);
			console.log(eventList);
			displayEvents();
			}
			else{
			//TODO - display error somewhere?
			}
		}
	};
	xhr.send();
}

function displayEvents(){
	//DOM
	
}