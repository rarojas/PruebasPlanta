/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import java.util.Date;

/**
 *
 * @author rrojase
 */
//Changes ALL
public class PruebaControlDTO implements java.io.Serializable {//Changes: implemntar Serializable

    public int termometro;
    public Integer presion;
    public Integer saquemarcha;
    public String sobrevelocidad;
    public String ubt;
    public Boolean operacionubt;
    public Boolean cargaliena;
    public Boolean altatemperatura;
    public Boolean bajapresion;
    public Boolean fallageneral;
    public Boolean cargadorbateria;
    public Boolean arranquemanual;
    public Boolean bajepresion;
    public Boolean temperatura;
    public Boolean proteccionsobrevelocidad;
    public Integer intentosarranque;
    public Integer duraciontotal;
    public String pruebacontrolcol;
    public int id;
    public int tipo;
    public EstadoPrueba estatus;
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Date dtInicio;
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Date dtFin;
    public String comentario;
    public IncidenciasDTO incidencias;
    public Integer aprueba;
    public Date dtAprueba;
    public Integer apruebaSupervisor;
    public Date dtApruebaSupervisor;

}
