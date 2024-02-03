import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { HojaServiciosComponent } from './hoja-servicios/hoja-servicios.component';
import { RoleGuardService } from 'src/app/shared/guards/roleGuard.service';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      {
        path: '', 
        redirectTo: 'hoja_servicio', 
        pathMatch: 'full',
      },
      {
        path: 'hoja_servicio',
        component: HojaServiciosComponent,
      },
      {
        path: 'usuarios',
        canActivate: [RoleGuardService],
        data: { expectedRole: 'ADMINISTRADOR' },
        component: UsuarioComponent,
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'hoja_servicio',
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
