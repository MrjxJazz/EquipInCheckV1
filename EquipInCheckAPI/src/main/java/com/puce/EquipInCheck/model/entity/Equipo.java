package com.puce.EquipInCheck.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String marca;
    private String modelo;
    private String procesador;
    private String optico;
    private String disco;
    private String otros;
    private String cargador;
    private String bandeja;
    private String serie;
    private String memoria;
    private String bateria;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private EstadoEquipo estadoEquipo;



    @Column(name = "condiciones_fisicas_esteticas_ingreso", columnDefinition = "TEXT")
    private String condicionesFisicasEsteticasIngreso;

    public Equipo() {
    }

    public Equipo(String tipo, String marca, String modelo, String procesador, String optico, String disco,
            String otros, String cargador, String bandeja, String serie, String memoria, String bateria,
            String condicionesFisicasEsteticasIngreso, String nombre, EstadoEquipo estadoEquipo) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.procesador = procesador;
        this.optico = optico;
        this.disco = disco;
        this.otros = otros;
        this.cargador = cargador;
        this.bandeja = bandeja;
        this.serie = serie;
        this.memoria = memoria;
        this.bateria = bateria;
        this.condicionesFisicasEsteticasIngreso = condicionesFisicasEsteticasIngreso;
        this.nombre = nombre;
        this.estadoEquipo = estadoEquipo;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getOptico() {
        return optico;
    }

    public void setOptico(String optico) {
        this.optico = optico;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getCargador() {
        return cargador;
    }

    public void setCargador(String cargador) {
        this.cargador = cargador;
    }

    public String getBandeja() {
        return bandeja;
    }

    public void setBandeja(String bandeja) {
        this.bandeja = bandeja;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getCondicionesFisicasEsteticasIngreso() {
        return condicionesFisicasEsteticasIngreso;
    }

    public void setCondicionesFisicasEsteticasIngreso(String condicionesFisicasEsteticasIngreso) {
        this.condicionesFisicasEsteticasIngreso = condicionesFisicasEsteticasIngreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoEquipo getEstadoEquipo() {
        return estadoEquipo;
    }

    public void setEstadoEquipo(EstadoEquipo estadoEquipo) {
        this.estadoEquipo = estadoEquipo;
    }


    

}
