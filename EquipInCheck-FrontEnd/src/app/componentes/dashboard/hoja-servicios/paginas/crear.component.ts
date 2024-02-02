import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HojaDeServicio } from '../model/hojaServicio';
import { HojaServicioService } from '../services/hoja-servicio.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import swall from 'sweetalert2';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class CrearComponent implements OnInit{ 

  clienteFormGroup: FormGroup;
  equipoFormGroup: FormGroup;
  idCliente: number ;
  idEquipo: number ;
  titulo_ventana: string = 'Nuevo Hoja de servicios';
  nombre_boton: string = 'Guardar'

  constructor(
    private _formBuilder: FormBuilder,
    private hojaservicioService: HojaServicioService,
    @Inject(MAT_DIALOG_DATA) public datoedit : any,
    private dialog : MatDialogRef<CrearComponent>
    ) {}


  ngOnInit() {

    this.equipoFormGroup = this._formBuilder.group({
      id: [null],
      nombre: ['', Validators.required,],
      tipo: ['', Validators.required],
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      procesador: ['', Validators.required],
      optico: ['', Validators.required],
      disco: ['', Validators.required],
      otros: ['', Validators.required],
      cargador: ['', Validators.required],
      bandeja: ['', Validators.required],
      serie: [
        '',
        [
          Validators.required,
          this.validarNumerico('serie'),
         
        ]
      ],
      memoria: ['', Validators.required],
      bateria: ['', Validators.required],
      condicionesFisicasEsteticasIngreso: ['', Validators.required]

    });
    
    this.clienteFormGroup = this._formBuilder.group({
      id: [null],
      empresa: [''],
      cargo: [''],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      telefono: [
        '',
        [
          Validators.required,
          this.validarNumerico('telefono'),
          this.validarLongitud(10, 'telefono')
        ]
      ],
      correo: ['', [Validators.required, Validators.email]],
      direccion: ['',Validators.required],
      ruc: [
        '',
        [
          Validators.required,
          this.validarNumerico('ruc'),
        ]
      ]
    });

    if(this.datoedit){

      this.titulo_ventana = 'Editar Hoja de servicios N° ' +  this.datoedit.ordenTrabajo;
      this.nombre_boton = 'Actualizar'

      this.hojaservicioService.buscarHojaServicioId(this.datoedit.id).subscribe(({
        next: (res) => {
          if (res.cliente) {
            this.idCliente = res.cliente.id;
            this.clienteFormGroup.patchValue(res.cliente);
          }
  
          if (res.equipo) {
            this.idEquipo = res.equipo.id;
            this.equipoFormGroup.patchValue(res.equipo);
          }
        },
        error: (error) => {
        }
      }))
    }
  }

  validarLongitud(longitud: number, campo: string) {
    return (control: FormControl) => {
      const valor = control.value;
      if (valor && valor.toString().length === longitud) {
        return null;
      } else {
        return { longitudIncorrecta: true };
      }
    };
  }
  
  validarNumerico(campo: string) {
    return (control: FormControl): { [key: string]: any } | null => {
      const esNumerico = /^[0-9]+$/.test(control.value);
  
      if (esNumerico) {
        return null;
      } else {
        return { pattern2: true };
      }
    };
  }

  guardar() {

    const usuarioJSON = sessionStorage.getItem('UsuarioLogeado');

    if (usuarioJSON) {
      const usuario = JSON.parse(usuarioJSON);
      const usuarioId = usuario?.id;
    
      if (usuarioId !== undefined && usuarioId !== null) {
    
        const hojaDeServicio: HojaDeServicio = {
          id: this.datoedit ? this.datoedit.id : null, 
          fechaIngreso: new Date(),
          ordenTrabajo: '',
          cliente: this.idCliente ? { ...this.clienteFormGroup.value, id: this.idCliente } : this.clienteFormGroup.value,
          equipo: this.idEquipo ? { ...this.equipoFormGroup.value, id: this.idEquipo } : this.equipoFormGroup.value,
          usuario: { id: usuarioId }
        };
    
        console.log(hojaDeServicio)
    
        const servicioObservable = this.datoedit ?
          this.hojaservicioService.actualizarHojaServicio(hojaDeServicio) :
          this.hojaservicioService.guardarHojaServicio(hojaDeServicio);
    
        servicioObservable.subscribe(res => {
          this.dialog.close("guardar")
          swall.fire({
            icon: 'success',
            confirmButtonColor: '#0275d8',
            html: `Se ${this.datoedit ? 'actualizó' : 'registró'} correctamente hoja de servicio:  <strong>${res.ordenTrabajo}</strong>`,
          });
        });
    
      } else {
        console.log('id error');
      }
    } else {
      console.log('usuario no encontrado');
    }
    
    
  }
  

  isValidField(field: string): boolean | null {
    return (
      this.clienteFormGroup.controls[field].errors &&
      this.clienteFormGroup.controls[field].touched
    );
  }

  getFieldError(field: string): string | null {
    if (!this.clienteFormGroup.controls[field]) return null;

    const errors = this.clienteFormGroup.controls[field].errors || {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        
        case 'required':
          return `${field} es requerido`;
        
        case 'pattern2':
          return `Tiene que ser númerico `;

        case 'longitudIncorrecta':
            const errorObj = errors[field];
            if (errorObj && 'requiredLength' in errorObj) {
                const longitudRequerida = errorObj.requiredLength;
                return `Obligatorio ${longitudRequerida} dígitos.`;
            } else {
                return 'Error de longitud';
            }
        
      
        case 'email':
          return `${field} no tiene el formato correcto`;

        case 'minlength':
            const minLength = errors['minlength']?.requiredLength;
            return `debe tener al menos ${minLength} digitos`;  
          
        case 'maxlength':
              const maxLength = errors['maxlength']?.requiredLength;
              return `debe tener con maximo ${maxLength} digitos`;
      }
    }
    return null;
  }

  isValidField2(field: string): boolean | null {
    return (
      this.equipoFormGroup.controls[field].errors &&
      this.equipoFormGroup.controls[field].touched
    );
  }

  getFieldError2(field: string): string | null {
    if (!this.equipoFormGroup.controls[field]) return null;

    const errors = this.equipoFormGroup.controls[field].errors || {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        
        case 'required':
          return `${field} es requerido`;
        
        case 'pattern2':
            return `Tiene que ser númerico `;
  
        case 'longitudIncorrecta':
          const errorObj = errors[field];
          if (errorObj && 'requiredLength' in errorObj) {
              const longitudRequerida = errorObj.requiredLength;
              return `Obligatorio ${longitudRequerida} dígitos.`;
          } else {
              return 'Error de longitud';
          }
          

        case 'email':
          return `${field} no tiene el formato correcto`;

        case 'minlength':
            const minLength = errors['minlength']?.requiredLength;
            return `debe tener al menos ${minLength} digitos`;
          
        case 'maxlength':
              const maxLength = errors['maxlength']?.requiredLength;
              return `debe tener con maximo ${maxLength} digitos`;
      }
    }
    return null;
  }



}
