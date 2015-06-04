package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Incidencias;

/**
 *
 * @author rrojase
 */
public class IncidenciaDTO {

    public String id;
    public String descripcion;
    
    public IncidenciaDTO(){}
    public IncidenciaDTO(Incidencias incidencia){
        this.id = incidencia.getId();
        this.descripcion = incidencia.getDescripcion();
    }
    
}
