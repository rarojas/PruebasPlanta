/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Incidencias;

/**
 *
 * @author rrojase
 */
public class IncidenciasDTO {

    public String descripcion;
    public String id;

    public IncidenciasDTO(Incidencias incidencias) {
        this.id = incidencias.getId();
        this.descripcion = incidencias.getDescripcion();
    }

    public IncidenciasDTO() {
    }

}
