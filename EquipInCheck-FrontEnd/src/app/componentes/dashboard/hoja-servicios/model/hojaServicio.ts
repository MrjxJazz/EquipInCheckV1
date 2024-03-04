// hoja-servicio.ts
import { Usuario } from '../../usuario/model/usuario';
import { Cliente } from './cliente';  
import { Equipo } from './equipo';    

export interface HojaDeServicio {
  id: number;
  fechaIngreso: Date;
  ordenTrabajo: string;
  cliente: Cliente;
  equipo: Equipo;

  usuario: Usuario;
}
