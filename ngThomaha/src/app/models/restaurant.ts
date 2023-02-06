export class Restaurant {
  id: number;
  name: string;
  location: string;
  description: string;
  food_type_id: number;
category_id: number;
rating_id: number;

constructor(

  id: number = 0,
  name: string ='',
  location: string ='',
  description: string='',
  food_type_id: number = 0,
category_id: number = 0,
rating_id: number = 0

){
  this.id=id;
  this.name=name;
  this.location=location;
  this.food_type_id=food_type_id;
this.category_id=category_id;
this.rating_id=rating_id;
this.description=description;
}
}
