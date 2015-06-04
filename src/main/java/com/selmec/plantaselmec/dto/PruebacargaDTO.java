/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Pruebacarga;

/**
 *
 * @author GEIDAR
 */
public class PruebacargaDTO{

    public int id;
    //public PruebaDTO prueba;
    public Double vmax;
    public Double vmin;
    public Double hmax;
    public Double hmin;

    public PruebacargaDTO() {
    }

    public PruebacargaDTO(Pruebacarga pruebaCarga) {
        this.id = pruebaCarga.getId();
        this.vmax = pruebaCarga.getVmax();
        this.vmin = pruebaCarga.getVmin();
        this.hmax = pruebaCarga.getHmax();
        this.hmin = pruebaCarga.getHmin();

        /*if (pruebaCarga.getPrueba() != null) {
            this.prueba = new PruebaDTO(pruebaCarga.getPrueba());
        }*/
    }
}
