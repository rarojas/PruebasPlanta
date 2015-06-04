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
public enum TipoConexion {

    Wire_4("Trifásica con Neutro"),
    Wire_3("Trifásica "),
    Split("Bifásica"),
    Mono("Monofásica");

    private final String text;

    /**
     * @param text
     */
    private TipoConexion(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
