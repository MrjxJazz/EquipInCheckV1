import { HojaDeServicio } from './../model/hojaServicio';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baseUrl from 'src/app/helpers';


@Injectable({
  providedIn: 'root'
})
export class HojaServicioService {


  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http: HttpClient) { }


  getHojasServicios(): Observable<HojaDeServicio[]>{
    return this.http.get<HojaDeServicio[]>(`${baseUrl}/api/hojas-de-servicio`);
  }

  guardarHojaServicio(hojaservicio: HojaDeServicio):Observable<HojaDeServicio>{
    return this.http.post<HojaDeServicio>(`${baseUrl}/api/hojas-de-servicio`, hojaservicio, {headers: this.httpHeaders});
  }

  buscarHojaServicioId(id:number): Observable<HojaDeServicio>{
    return this.http.get<HojaDeServicio>(`${baseUrl}/api/hojas-de-servicio/${id}`);
  }

  actualizarHojaServicio(hojaservicio: HojaDeServicio):Observable<HojaDeServicio>{
    return this.http.put<HojaDeServicio>(`${baseUrl}/api/hojas-de-servicio/${hojaservicio.id}`, hojaservicio, {headers: this.httpHeaders});
  }

  eliminarHojaServicio(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/api/hojas-de-servicio/${id}`);
  }



  


}
