import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../login/model/usuario-logeo';
import { LoginService } from '../../login/login.service';
import { SessionService } from '../../login/session.service';
import { TokenService } from '../../../shared/services/token.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css'] 
  })
  export class NavbarComponent implements OnInit {

    usuario: Usuario;
    rolInicioSesion:string;
  
    constructor(
      private servicio: LoginService,
      public servicioSession: SessionService,
      private tokenService: TokenService,
      private router: Router
      ) { }
  
    ngOnInit(): void {
  
      this.usuario = this.servicioSession.getUsuarioLogeado();
    }
  
    logOut() {
      this.tokenService.logOut();
      this.router.navigate(['/login'])
    }
  
  }