

export class Roles{
    id:number;
    descripcion:string;
    seleccion?: boolean;
}



export interface Usuario {
    id: number;
    nombre?: string;
    apellido?: string;
    correoElectronico?: string;
    contrasena?: string;
    telefono?: string;
    cedula?: string;
    tipoUsuario?: string;
    estado?: boolean;
  }
  