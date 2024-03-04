
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import swall from 'sweetalert2';
import { HojaDeServicio } from './model/hojaServicio';
import { HojaServicioService } from './services/hoja-servicio.service';
import { CrearComponent } from './paginas/crear.component';



@Component({
  selector: 'app-hoja-servicios',
  templateUrl: './hoja-servicios.component.html',
  styleUrls: ['./hoja-servicios.component.css']
})
export class HojaServiciosComponent implements AfterViewInit , OnInit{
  
  estadoFiltro:any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  columnas: string[] = ['ORDEN', 'FECHA INGRESO', 'CLIENTE', 'CEDULA', 'EQUIPO', 'TIPO EQUIPO', 'TECNICO','ESTADO','ACCIONES'];
  dataSource = new MatTableDataSource<HojaDeServicio>;

  constructor(
    private servicio:HojaServicioService,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.listarHojaServicios();
  }

  ngAfterViewInit(): void {
    this.paginator._intl.itemsPerPageLabel = 'Paginas';
    this.paginator._intl.nextPageLabel = 'Siguiente';
    this.paginator._intl.previousPageLabel = 'Atras';
    this.dataSource.paginator = this.paginator;
  }

  aplicarFiltro(valor: string) {
    // Convierte el valor de entrada a minúsculas
    const filtro = valor.trim().toLowerCase();
    
    // Aplica el filtro solo si el valor de entrada es un número
    if (!isNaN(Number(filtro))) {
      this.dataSource.filterPredicate = (data, filter) => {
        return data.cliente.ruc.toLowerCase().includes(filter);
      };
      this.dataSource.filter = filtro;
    } 
  
    // Si se está utilizando paginación, vuelve a la primera página después de aplicar el filtro
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }


  

  

  

  listarHojaServicios(){
    return this.servicio.getHojasServicios().subscribe(
      {next: res => {
        console.log(res)
        this.dataSource = new MatTableDataSource(res)
        this.dataSource.paginator = this.paginator;
        },
        error: error => {
          console.log("Ocurrio un error en la carga")
        }
      }
    )
  }

  cargarHojaServicio(){
    return this.servicio.getHojasServicios();
  }
  
  abrirDialogoNuevoHojaServicio() {
    this.dialog.open(CrearComponent, {
         width:'650px',
     }).afterClosed().subscribe(valor =>{
        if (valor === 'guardar') {
          this.listarHojaServicios();
       }
    });
  }

  editarHojaServicio(fila: any){
    this.dialog.open(CrearComponent,{
      width:'650px',
      data:fila
    }).afterClosed().subscribe(valor =>{
      if (valor === 'guardar') {
        this.listarHojaServicios();
      }
    });
  }

  eliminarHojaServicio(fila: any): void {


    const pregunta = `¿Estás seguro de eliminar a Hoja de servicio con la orden <strong>${fila.id}</strong>?`;
    const mensajeBoton = `Sí, eliminar!`;
    const mensajeConfirmacion = `Hoja Servicio eliminado con éxito!`;

    swall.fire({
      html: pregunta,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#0275d8',
      cancelButtonColor: '#9c9c9c',
      confirmButtonText: mensajeBoton,
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.servicio.eliminarHojaServicio(fila.id).subscribe({
          next: (res) => {
            this.listarHojaServicios();
          },
          error: (error) => {
            console.error('Ocurrió un error', error);
          },
        });

        swall.fire({
          icon: 'success',
          html: mensajeConfirmacion,
        });
      }
    });
  }

}


