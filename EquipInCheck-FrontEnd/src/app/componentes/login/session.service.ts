import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { Usuario } from './model/usuario-logeo';




const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUserName';
const USUARIO_LOGEADO = 'UsuarioLogeado';
const ROL_NOMBRE  = 'RolNombre'
const USUARIO_NOMBRE  = 'UsuarioNombre'

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  roles: Array<string> = [];

  constructor() {
  }


  public setRolNombre(rolNombre: string) {
    window.sessionStorage.removeItem(ROL_NOMBRE);
    window.sessionStorage.setItem(ROL_NOMBRE, rolNombre);
  }

  public getRolNombre(): string | null {
    return sessionStorage.getItem(ROL_NOMBRE);
  }

  public setUsuarioNombre(nombre: string) {
    window.sessionStorage.removeItem(USUARIO_NOMBRE);
    window.sessionStorage.setItem(USUARIO_NOMBRE, nombre);
  }

  public getUsuarioNombre(): string | null {
    return sessionStorage.getItem(USUARIO_NOMBRE);
  }

  public logOut(): void {
    window.sessionStorage.clear();
  }

  public estaAutenticado(): boolean {
    if(this.getUsuarioLogeado() != null ){
      return true;
    }else{

      return false;
    }
  }

  public setUsuarioLogeado(usuario: Usuario) {
      window.sessionStorage.removeItem(USUARIO_LOGEADO);
      window.sessionStorage.setItem(USUARIO_LOGEADO, JSON.stringify(usuario));
  }

  public getUsuarioLogeado(): any | null {
    return sessionStorage.getItem(USUARIO_LOGEADO);
  }
}
