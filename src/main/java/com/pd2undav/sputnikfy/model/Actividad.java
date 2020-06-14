package com.pd2undav.sputnikfy.model;

public class Actividad {
    private String usuario;
    private String tipo;
    private Ambito ambito;
    private String cancion;
    private String detail;
    private String valor;
    private Entidad entidad;

    public Actividad() {
        super();
    }

    // Constructor Escucha
    public Actividad(String tipo, Ambito ambito, String cancion) {
        this.tipo = tipo;
        this.ambito = ambito;
        this.cancion = cancion;
    }

    // Constructor Tweet
    public Actividad(String tipo, String cancion, String detail) {
        this.tipo = tipo;
        this.cancion = cancion;
        this.detail = detail;
    }

    // Constructor Pulgar
    public Actividad(String tipo, String valor, Entidad entidad) {
        this.tipo = tipo;
        this.valor = valor;
        this.entidad = entidad;
    }

    public String getTipo() {
        return tipo;
    }

    public Ambito getAmbito() {
        return ambito;
    }

    public String getCancion() {
        return cancion;
    }

    public String getDetail() {
        return detail;
    }

    public String getValor() {
        return valor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
