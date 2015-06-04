/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.selmec.plantaselmec.Models.EstadoPruebaArranque;
import java.util.Date;

/**
 *
 * @author GEIDAR
 */
public class InstalacionDTO implements java.io.Serializable {

    public int id;
    public boolean ventilacion;
    public boolean orientacion;
    public boolean nivel;
    public boolean silenciador;
    public boolean combustible;
    public boolean aterrizado;
    public boolean neutro;
    public boolean fuerza;
    public boolean precalentador;
    public boolean ubt;

    public int tipo;
    public EstadoPruebaArranque estatus;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtInicio;
    @JsonDeserialize(using = DateDeserializer.class)
    public Date dtFin;
    public String comentario;
    public IncidenciasDTO incidencias;

}
