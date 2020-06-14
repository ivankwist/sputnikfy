package com.pd2undav.sputnikfy;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;

public class Agregado {
    private String usuario;
    @JacksonXmlElementWrapper(localName = "actividades")
    private List<Actividad> actividades;

    public Agregado() {
        super();
    }

    public Agregado(String usuario, List<Actividad> actividades) {
        this.usuario = usuario;
        this.actividades = actividades;
    }

    public String getUsuario() {
        return usuario;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
}
