/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Cariles;

/**
 *
 * @author rrojase
 */
//Changes faltaban algunos atributos 
public class CarrilDTO implements java.io.Serializable {

    public Integer id;
    public int noCarril;
    public String equipo;
    public String planta;
    public Integer maxCap;
    public Integer minCap;
    public String descripcion;
    
    public CarrilDTO() {
    }

    public CarrilDTO(Cariles carril) {
        this.id = carril.getId();
        this.noCarril = carril.getNoCarril();
        this.equipo = carril.getEquipo();
        this.planta = carril.getPlanta();
        this.maxCap = carril.getMaxCap();
        this.minCap = carril.getMinCap();
        this.descripcion = carril.getDescripcion();
        //this.ensambles=carril.getEnsambles();//Porque sea hace un DTO de Carriles,esque por lo que veo es que no habría problema de utilizar la entidad de carriles, sin embargo supongo que es por el patrón utilizado
    }

    /**
     * @return the maxCap
     */
    public Integer getMaxCap() {
        return maxCap;
    }

    /**
     * @param maxCap the maxCap to set
     */
    public void setMaxCap(Integer maxCap) {
        this.maxCap = maxCap;
    }

    /**
     * @return the minCap
     */
    public Integer getMinCap() {
        return minCap;
    }

    /**
     * @param minCap the minCap to set
     */
    public void setMinCap(Integer minCap) {
        this.minCap = minCap;
    }

}
