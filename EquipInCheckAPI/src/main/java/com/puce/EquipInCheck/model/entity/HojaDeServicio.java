package com.puce.EquipInCheck.model.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hojas_servicio")
public class HojaDeServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    private String ordenTrabajo;



    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @PrePersist
    protected void onCreate() {
        fechaIngreso = new Date();
    }


    public HojaDeServicio() {
    }

    public HojaDeServicio(String ordenTrabajo, Cliente cliente, Equipo equipo, Usuario usuario) {
        this.ordenTrabajo = ordenTrabajo;
        this.cliente = cliente;
        this.equipo = equipo;
        this.usuario = usuario;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   




}

