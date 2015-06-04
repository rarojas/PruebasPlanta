/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rrojase
 */
public class Produccion implements Serializable {

    private Integer ID;
    private String OP;
    private EstadoProduccion Estatus;
    private String ItemID;
    private String descripcion;
    private Date dtEstimada;
    private Date dtProgramada;
    private Date dtIniciado;
    private Date dtTerminado;
    private Date dtReal;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getOP() {
        return OP;
    }

    public void setOP(String OP) {
        this.OP = OP;
    }

    public EstadoProduccion getEstatus() {
        return Estatus;
    }

    public void setEstatus(EstadoProduccion Estatus) {
        this.Estatus = Estatus;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDtEstimada() {
        return dtEstimada;
    }

    public void setDtEstimada(Date dtEstimada) {
        this.dtEstimada = dtEstimada;
    }

    public Date getDtProgramada() {
        return dtProgramada;
    }

    public void setDtProgramada(Date dtProgramada) {
        this.dtProgramada = dtProgramada;
    }

    public Date getDtIniciado() {
        return dtIniciado;
    }

    public void setDtIniciado(Date dtIniciado) {
        this.dtIniciado = dtIniciado;
    }

    public Date getDtTerminado() {
        return dtTerminado;
    }

    public void setDtTerminado(Date dtTerminado) {
        this.dtTerminado = dtTerminado;
    }

    public Date getDtReal() {
        return dtReal;
    }

    public void setDtReal(Date dtReal) {
        this.dtReal = dtReal;
    }

}
