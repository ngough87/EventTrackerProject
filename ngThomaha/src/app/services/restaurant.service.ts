import { Restaurant } from '../models/restaurant';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  url = environment.baseUrl + 'api/restaurants';
  constructor(
    private http: HttpClient
  ) { }

  index(): Observable <Restaurant[]>{

    return this.http.get<Restaurant[]>(this.url).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError(
        () =>
          new Error(
            'RestaurantService.index(): error retrieving restaurants: ' + err
          )
      );
    })
  );
}


  //TODO: show, create, update, destroy



  show(restaurantId : number) : Observable<Restaurant>{
    return this.http.get<Restaurant>(`${this.url}/${restaurantId}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            'RestaurantService.index(): error retrieving restaurants: ' + err

          )
        )
      } )
    )
  }



  create(restaurant: Restaurant ): Observable<Restaurant> {


    const httpOptions = {
      headers: {
        'Content-Type': 'application/json'
      }
    };

    return this.http.post<Restaurant>(this.url, restaurant).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'RestaurantService.create(): error creating restaurants: ' + err
            )
        );
      })
    );
  }


  update(restaurant: Restaurant): Observable<Restaurant> {
    headers: {
      'Content-Type'; 'application/json'
    };
    return this.http.put<Restaurant>(`${this.url}/${restaurant.id}`, restaurant).pipe(

      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error(
              'RestaurantService.create(): error creating restaurants: ' + err
            )
        );
      })


    );
  }


  destroy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () =>
            new Error('RestaurantService.create(): error creating restaurants: ' + err
            )
        );
      })
    );
  }

}
