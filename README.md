# EventTrackerProject

## Overview

The goal of this project was create a Spring Boot REST project in conjunction with REST API controllers, services and Spring Data JPA repositories. This project will serve as the back-end and I will build upon it to create an app called  "ThOmaha". 

Starting with database/schema creation in MySql Workbench, I built a small database with 6 tables. After completing the database, I moved on to JPA project creation in eclipse wherein I created 6 entities for corresponding database tables, in doing so I mapped all entity classes appropriately. After JPA completion, I started on the Spring Boot project, wherein I completed all controller and service logic to perform full CRUD on each entity totaling in 24 classes/interfaces of a REST API. I then tested controller routes using Postman. Overall this project was easy and fun, however time consuming considering the amount of class/interfaces/packages needed for each entity. 

I returned to this project to add JSON and SpringREST with JSON handling the JavaScript. This leg of the project consisted of adding CRUD opertations to a front end application. By implementing this, the user is able to perform CRUD in the event entity. I started by ensuring all of my entites were still mapped correctly via postman. I then created forms for both a create event and update event funtion. Additionally, I created a table to view all records. This portion is far from complete as I was unable to complete the delete and update functions successfully.  

AWS:
http://18.189.201.125:8080/thOmaha/api


## Expected Routes

| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List< Event > | GET api/events    |Gets all events |
| Event   | GET api/events/{eventId}       |Gets one event by id|
| Event   | POST api/events       |Creates a new event|
| Event   | PUT api/events/{eventId}       |Replaces an existing event by id|
| void   | DELETE api/events/{eventId}      |Deletes an existing post by id|


| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List < Category > | GET api/categories    |Gets all categories|
| Category   | GET api/categories/{categoryid}       |Gets one category by id|
| Category   | POST api/categories       |Creates a new category|
| Category   | PUT api/categories/{categoryid}       |Replaces an existing category by id|
| void   | DELETE api/categories/{categoryid}      |Deletes an existing category by id|


| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List < Restaurant > | GET api/restaurants    |Gets all restaurants|
| Restaurant   | GET api/restaurants/{restaurantId}       |Gets one restaurant by id|
| Restaurant   | POST api/restaurants       |Creates a new restaurant |
| Restaurant   | PUT api/restaurants/{restaurantId}       |Replaces an existing restaurant  by id|
| void   | DELETE api/restaurants/{restaurantId}      |Deletes an existing restaurant  by id|


| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List < EventType > | GET api/eventTypes    |Gets all eventType |
| EventType   | GET api/eventTypes/{eventTypeId}       |Gets one eventType by id|
| EventType  | POST api/eventTypes       |Creates a new eventType|
| EventType   | PUT api/eventTypes/{eventTypeId}       |Replaces an existing eventType by id|
| void   | DELETE api/eventTypes/{eventTypeId}      |Deletes an existing eventType by id|

| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List < FoodType > | GET api/foodTypes   |Gets all foodTypes  |
| FoodType   | GET api/foodTypes /{foodTypeId}       |Gets one foodType  by id|
| FoodType  | POST api/foodTypes       |Creates a new foodType|
| FoodType  | PUT api/foodTypes {foodTypeId}       |Replaces an existing foodType by id|
| void   | DELETE api/foodTypes /{foodTypeId}      |Deletes an existing foodType by id|

| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
| List < Rating > | GET api/ratings   |Gets all ratings  |
| Rating   | GET api/ratings /{ratingId}       |Gets one rating  by id|
| Rating   | POST api/ratings       |Creates a new ratings |
| Rating   | PUT api/ratings/{ratingId}       |Replaces an existing ratings  by id|
| void   | DELETE api/ratings/{ratingId}      |Deletes an existing ratings  by id|

## Technologies Used
  - Java
  - REST
  - Spring Web
  - Spring Data JPA
  - MySQL Workbench
  - AWS EC2
  - MySQL Driver
  - Git
  - Gradle
  - Eclipse

## Lesson Learned

- I learned a lot when it came to the database creation. It was a small one, but this is the first time I created one with this many tables solo. It was definitely a learning experience deciphering sql error codes. Overall, I enjoyed working in MySQL workbench, despite its many nuances.

<img width="606" alt="Screenshot 2023-01-21 at 12 17 14 PM" src="https://user-images.githubusercontent.com/74070200/213881266-b418f68d-46ca-4fc9-84e6-28d1846679be.png">




- I had an issue with naming convention. I misspelled the word "restaurant" in one location. It took quite some time to locate the misspelled word as it was not popping out with an error or anything like that.

- I think I finally have a good understanding of entity mapping. In the past I struggled with this concept. I think with this project, it finally clicked. That felt really good. 

- This is not a lessons learned, but I am looking forward to adding a front-end to this project. 
- Adding the CRUD operations was especially difficult. I had a lot of troule with updating and deleting events. I suspect this may be due to the fact that I have so many tables. Unfortunately, I was not able to finish these functions in the alotted time. I will be returning to this to complete in the coming days.
- Getting forms to work properly was difficult. I couldn't figure out why some of my buttons were not appearing. 

