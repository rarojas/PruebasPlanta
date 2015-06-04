/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Models;

/**
 *
 * @author rrojase
 */
public enum EstadoProduccion {

    Programado(2),
    Iniciado(4),
    Terminado(7);

    private final int value;

    private EstadoProduccion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
