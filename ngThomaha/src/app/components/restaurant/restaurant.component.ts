import { Component } from '@angular/core';
import { Restaurant } from 'src/app/models/restaurant';
import { RestaurantService } from 'src/app/services/restaurant.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent {

  restaurants: Restaurant[]= [];
  selected: null | Restaurant = null;
  newRestaurant: Restaurant = new Restaurant();
  editRestaurant: Restaurant | null = null;




  constructor(private restaurantService: RestaurantService,
    ){

  }

  ngOnInit(){
    this.reload();
  }

reload(){
  this.restaurantService.index().subscribe({
    next:(data) =>{
      this.restaurants = data;
      console.log(data);
    },
    error:(oof) =>{
      console.error('Error loading RestaurantList');
      console.error(oof);
    }


  });


}



displayRestaurant(restaurant: Restaurant){
  this.selected = restaurant;
}

displayTable() {
  this.selected = null;
}

addRestaurant(restaurant: Restaurant)  {
  this.restaurantService.create(restaurant).subscribe({
    next: (data) => {
    this.newRestaurant = new Restaurant();
      this.reload();
    },
    error: (err) => {
      console.error('RestaurantComponent.addRestaurant()): error creating Restaurant:');
      console.error(err);
    },
  });

}
setEditRestaurant() {
  this.editRestaurant = Object.assign({}, this.selected);
}



deleteRestaurant(id: number) {
  this.restaurantService.destroy(id).subscribe({

    next: (data) =>{

      this.reload();
    },
    error:(err) => {
      console.error('Restaurant.reload(): error loading Restaurant');
      console.error(err);
    }

  });


}




updateRestaurant(restaurant: Restaurant, goToDetail = true): void {
  this.restaurantService.update(restaurant).subscribe({
    next: (updateRestaurant) => {
      if(goToDetail){
        this.selected = updateRestaurant;
        this.reload();
      }
      else{
        this.selected = null;
      }
      this.reload();

    },
    error: (toobad) =>{
      console.error('RestaurantListComponent.updateRestaurant: error updating');
      console.error(toobad);

    }
  });
}
}
