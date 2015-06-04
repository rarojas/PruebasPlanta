/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.EstadoPruebaArranque;
import java.util.Date;

/**
 *
 * @author plantas
 */
public class PruebaArranqueDTO {

    public int id;
    public int tipo;
    public EstadoPruebaArranque estatus;
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Date dtInicio;
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Date dtFin;
    public String comentario;
    public IncidenciasDTO incidencias;
}
