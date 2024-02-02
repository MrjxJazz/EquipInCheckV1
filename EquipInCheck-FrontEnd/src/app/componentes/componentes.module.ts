import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HojaServiciosComponent } from './dashboard/hoja-servicios/hoja-servicios.component';
import { NavbarComponent } from './dashboard/navbar/navbar.component';
import { UsuarioComponent } from './dashboard/usuario/usuario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [
    LoginComponent,
  ],
  
  exports:[
    LoginComponent,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
  ]
})
export class ComponentesModule { }
