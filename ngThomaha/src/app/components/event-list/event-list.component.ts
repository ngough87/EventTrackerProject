import { EventService } from '../../services/event.service';
import { Component } from '@angular/core';
import { Event } from 'src/app/models/event'

@Component({
  selector: 'app-home',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent {
  title = 'ngThomaha';
  events: Event[]= [];
  selected: null | Event = null;
  newEvent: Event = new Event();
  editEvent: Event | null = null;

  constructor(private eventService: EventService,
    ){

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



displayEvent(event: Event){
  this.selected = event;
}

displayTable() {
  this.selected = null;
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
setEditEvent() {
  this.editEvent = Object.assign({}, this.selected);
}



deleteEvent(id: number) {
  this.eventService.destroy(id).subscribe({

    next: (data) =>{

      this.reload();
    },
    error:(err) => {
      console.error('Event.reload(): error loading event');
      console.error(err);
    }

  });


}




updateEvent(event: Event, goToDetail = true): void {
  this.eventService.update(event).subscribe({
    next: (updateEvent) => {
      if(goToDetail){
        this.selected = updateEvent;
        this.reload();
      }
      else{
        this.selected = null;
      }
      this.reload();

    },
    error: (toobad) =>{
      console.error('EventListComponent.updateEvent: error updating');
      console.error(toobad);

    }
  });
}

}
