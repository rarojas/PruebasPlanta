/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Models.Reports;

/**
 *
 * @author ricardo
 */
public enum ColorEnsamble {

    Programada("#2BA000"),
    EnEjecucion("#EFEF00"),
    Terminada("#2A15DF"),
    Rechazada("#E90011"),
    Aprobada("#C103DB");

    private final String name;

    private ColorEnsamble(String s) {
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
