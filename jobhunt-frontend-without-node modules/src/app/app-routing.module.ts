import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ListJobsComponent } from './components/list-jobs/list-jobs.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { LoginGuard } from './guards/login.guard';

const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:'full'},
  {
    path: 'registerUser',
    component: RegisterComponent,
  },
  {
    path:'loginUser',
    component:LoginComponent
  },
  {
    path:'listjobs',
    component:ListJobsComponent,
    canActivate:[LoginGuard]
  },
  {
    path:'watchlist',
    component:WatchlistComponent,
    canActivate:[LoginGuard]
  },
  {
    path:'userDetails',
    component:UserDetailsComponent,
    canActivate:[LoginGuard]
  },
  {
    path:'home',
    component:HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
