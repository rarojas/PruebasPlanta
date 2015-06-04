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
public enum EstadoEnsamble {

    Programada("Programada"),
    EnEjecucion("En Ejecucion"),
    Terminada("Terminada"),
    Rechazada("Rechazada"),
    Aprobada("Aprobada");

    private final String name;

    private EstadoEnsamble(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    @Override
    public String toString() {
        return name;
    }
}
