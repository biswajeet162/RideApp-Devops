import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DriverDashboardComponent } from './driver-dashboard/driver-dashboard.component';
import { DriverComponent } from './driver/driver.component';
import { LoginComponent } from './login/login.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user', component: UserComponent },
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'driver', component: DriverComponent },
  { path: 'driver-dashboard', component: DriverDashboardComponent },
  { path: 'ride', component: UserComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
