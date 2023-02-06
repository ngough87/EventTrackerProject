import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { EventListComponent} from './components/event-list/event-list.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RestaurantComponent } from './components/restaurant/restaurant.component';

const routes: Routes = [

{ path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'restaurant', component: RestaurantComponent },
  { path: 'restaraunt/:id', component: RestaurantComponent },
  { path: 'event', component: EventListComponent },
  { path: 'event/:id', component: EventListComponent },


   { path: '**', component: NotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
