/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Prueba;
import java.util.Date;

public class PruebaDTO {

    public int id;
    public EnsambleDTO ensamble;
    public int tipo;
    public EstadoPrueba estatus;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtInicio;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtFin;
    public String comentario;
    public IncidenciasDTO incidencias;   
    public Integer aprueba;
    public Date dtAprueba;
    public Integer apruebaSupervisor;
    public Date dtApruebaSupervisor;    

    public PruebaDTO() {
    }

    public PruebaDTO(Prueba prueba) {
        this.id = prueba.getId();
        this.dtInicio = prueba.getDtInicio();
        this.dtFin = prueba.getDtFin();
        this.estatus = prueba.getEstatus();
        this.tipo = prueba.getTipo();
        this.comentario = prueba.getComentario();
        //Changes
        this.aprueba = prueba.getAprueba();
        this.dtAprueba = prueba.getDtAprueba();
        this.apruebaSupervisor = prueba.getApruebaSupervisor();
        this.dtApruebaSupervisor = prueba.getDtApruebaSupervisor();
        if (prueba.getIncidencias() != null) {
            this.incidencias = new IncidenciasDTO(prueba.getIncidencias());
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
