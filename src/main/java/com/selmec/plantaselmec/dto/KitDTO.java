/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Kit;

/**
 *
 * @author rrojase
 */
public class KitDTO {

    public Integer id;
    public String equipo;
    public String descripcion;

    public KitDTO() {
    }

    public KitDTO(Kit kit) {
        this.id = kit.getId();
        this.equipo = kit.getEquipo();
        this.descripcion = kit.getDescripcion();

    }
}
