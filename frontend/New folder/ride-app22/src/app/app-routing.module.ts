import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  // { path: 'signup', component: SignupComponent },
  { path: 'user', component: UserComponent },
  // { path: 'driver', component: DriverComponent },
  // { path: 'ride', component: RideComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
