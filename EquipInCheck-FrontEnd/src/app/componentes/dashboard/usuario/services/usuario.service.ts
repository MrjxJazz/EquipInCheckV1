import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Roles, Usuario } from '../model/usuario';
import baseUrl from 'src/app/helpers';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http: HttpClient) { }


  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(`${baseUrl}/api/usuarios`);
  }

  buscarUsuario(id:number): Observable<Usuario>{
    return this.http.get<Usuario>(`${baseUrl}/api/usuarios/${id}`);
  }

  guardarUsuarioServi(usuario: Usuario):Observable<Usuario>{
    return this.http.post<Usuario>(`${baseUrl}/api/usuarios`, usuario, {headers: this.httpHeaders});
  }

  cambiarEstado(id: number, estado: boolean): Observable<any> {
    return this.http.post(`${baseUrl}/api/usuarios/cambiar-estado/${id}/${estado}`, {});
  }

  validarcorreo(correo: string): Observable<boolean>{
    return this.http.get<boolean>(`${baseUrl}/api/usuarios/existe-correo?correoElectronico=${correo}`);
  }

  eliminarUsuario(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/api/usuarios/${id}`);
  }
  



}
