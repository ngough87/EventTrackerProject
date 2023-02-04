
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, pipe, throwError } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from 'src/environments/environment.development';
import { Event } from 'src/app/models/event';

@Injectable({
  providedIn: 'root'
})
export class EventService {

url = environment.baseUrl + 'api/events';
  constructor(
    private http: HttpClient
  ) { }

  index(): Observable <Event[]>{

    return this.http.get<Event[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error(
            'TodoService.index(): error retrieving pokemon: ' + err
          )
      );
    })
  );
}


  //TODO: show, create, update, destroy



  show(eventId : number) : Observable<Event>{
    return this.http.get<Event>(`${this.url}/${eventId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'EventService.index(): error retrieving events: ' + err

          )
        )
      } )
    )
  }



  create(event: Event ): Observable<Event> {
    // event.completed=false;
    // event.description='';

    const httpOptions = {
      headers: {
        'Content-Type': 'application/json'
      }
    };

    return this.http.post<Event>(this.url, event).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'EventService.create(): error creating events: ' + err
            )
        );
      })
    );
  }




}
