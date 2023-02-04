import { EventService } from './../../services/event.service';
import { Component } from '@angular/core';
import { Event } from 'src/app/models/event'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  events: Event[]= [];
  selected: null | Event = null;
  newEvent: Event = new Event();

  constructor(private eventService: EventService){

  }

  ngOnInit(){
    this.reload();
  }

reload(){
  this.eventService.index().subscribe({
    next:(data) =>{
      this.events = data;
      console.log(data);
    },
    error:(oof) =>{
      console.error('Error loading eventList');
      console.error(oof);
    }


  });


}

displayEvents(event: Event){
  this.selected = event;
}



addEvent(event: Event)  {
  this.eventService.create(event).subscribe({
    next: (data) => {
    this.newEvent = new Event();
      this.reload();
    },
    error: (err) => {
      console.error('EventComponent.addEvent()): error creating event:');
      console.error(err);
    },
  });

}


}
