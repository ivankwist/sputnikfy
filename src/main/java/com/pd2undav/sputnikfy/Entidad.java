package com.pd2undav.sputnikfy;

public class Entidad {
    private String entidad_tipo;
    private String entidad_id;

    public Entidad() {
        super();
    }

    public Entidad(String entidad_tipo, String entidad_id) {
        this.entidad_tipo = entidad_tipo;
        this.entidad_id = entidad_id;
    }

    public String getEntidad_tipo() {
        return entidad_tipo;
    }

    public void setEntidad_tipo(String entidad_tipo) {
        this.entidad_tipo = entidad_tipo;
    }

    public String getEntidad_id() {
        return entidad_id;
    }

    public void setEntidad_id(String entidad_id) {
        this.entidad_id = entidad_id;
    }
}
