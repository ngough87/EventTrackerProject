export class Event {

  id: number;
  name: string;
  location: string;
  date: string;
  description: string;
  image: string;
  event_type_id: number;
category_id: number;
rating_id: number;

  constructor(

    id: number =  0,
    name: string = '',
    location: string= '',
    date: string ='',
    description: string='',
    image: string='',
    event_type_id: number=0,
    category_id: number=0,
    rating_id: number=0
  ){

    this.id= id;
    this.name= name;
    this.location= location;
    this.date= date;
    this.description= description;
    this.image= image;
    this.event_type_id=event_type_id;
    this.category_id= category_id;
    this.rating_id= category_id;
  }
}
