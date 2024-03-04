import { CommonModule } from '@angular/common';
import { Component , OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import swall from 'sweetalert2'; 
import { LoginService } from './login.service';
import { SessionService } from './session.service';
import { Usuario } from './model/usuario-logeo';
import { TokenService } from '../../shared/services/token.service';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
  })
export class LoginComponent implements OnInit{


  formulario: FormGroup;
  usuario: Usuario;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private servicioLogin: LoginService,
    private tokenService: TokenService,
    private servicioSession: SessionService
  ) {
    this.formulario = this.fb.group({
      usuario: ['admin@gmail.com', Validators.required],
      password: ['admin', Validators.required],
    });
  }

  ngOnInit(): void {}

  ingresar() {
    const correo = this.formulario.value.usuario;
    const contrasena = this.formulario.value.password;

    this.servicioLogin.validarInicioSesion(correo, contrasena).subscribe({
      next: (usuario: Usuario) => {
        this.servicioSession.setUsuarioLogeado(usuario);
        this.servicioSession.setRolNombre(usuario.tipoUsuario);
        this.servicioSession.setUsuarioNombre(usuario.nombre);

        this.tokenService.setToken(usuario.contrasena!);
        this.tokenService.setUserName(usuario.nombre!);
        this.tokenService.setAuthorities([usuario.tipoUsuario]!);
       
        swall.fire({
          html: `<strong>${usuario.nombre.toLowerCase()}</strong> Iniciaste sesión como: <strong>${usuario.tipoUsuario.toUpperCase()}</strong>`,
          icon: 'success',
          confirmButtonColor: '#0275d8',
        });
        this.router.navigate(['dashboard']);
      },
      error: (error: any) => {
        swall.fire({
          html: 'Error al iniciar sesión',
          icon: 'error',
          confirmButtonColor: '#d80227',
        });
      },
    });
  }

  setServicioRol(rol: any) {

  }

 }
