/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.selmec.plantaselmec.vo;

import java.io.Serializable;

/**
 *
 * @author habil
 */
public class CargaSubitaVo implements Serializable{
    
    private Integer ID;
    private Integer SEG;
    private Double VINICIO;
    private Double VFINAL;
    private Double HFINAL;
    private Double HINICIO;
    private Double ICARGA;
    private Integer PRUEBAID;
    private Integer ITERACION;
    
    
    //GETTERS AND SETTERS---------------------------------------------------------------------------------------
    /**
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * @return the SEG
     */
    public Integer getSEG() {
        return SEG;
    }

    /**
     * @param SEG the SEG to set
     */
    public void setSEG(Integer SEG) {
        this.SEG = SEG;
    }

    /**
     * @return the VINICIO
     */
    public Double getVINICIO() {
        return VINICIO;
    }

    /**
     * @param VINICIO the VINICIO to set
     */
    public void setVINICIO(Double VINICIO) {
        this.VINICIO = VINICIO;
    }

    /**
     * @return the VFINAL
     */
    public Double getVFINAL() {
        return VFINAL;
    }

    /**
     * @param VFINAL the VFINAL to set
     */
    public void setVFINAL(Double VFINAL) {
        this.VFINAL = VFINAL;
    }

    /**
     * @return the HFINAL
     */
    public Double getHFINAL() {
        return HFINAL;
    }

    /**
     * @param HFINAL the HFINAL to set
     */
    public void setHFINAL(Double HFINAL) {
        this.HFINAL = HFINAL;
    }

    /**
     * @return the HINICIO
     */
    public Double getHINICIO() {
        return HINICIO;
    }

    /**
     * @param HINICIO the HINICIO to set
     */
    public void setHINICIO(Double HINICIO) {
        this.HINICIO = HINICIO;
    }

    /**
     * @return the ICARGA
     */
    public Double getICARGA() {
        return ICARGA;
    }

    /**
     * @param ICARGA the ICARGA to set
     */
    public void setICARGA(Double ICARGA) {
        this.ICARGA = ICARGA;
    }

    /**
     * @return the PRUEBAID
     */
    public Integer getPRUEBAID() {
        return PRUEBAID;
    }

    /**
     * @param PRUEBAID the PRUEBAID to set
     */
    public void setPRUEBAID(Integer PRUEBAID) {
        this.PRUEBAID = PRUEBAID;
    }

    /**
     * @return the ITERACION
     */
    public Integer getITERACION() {
        return ITERACION;
    }

    /**
     * @param ITERACION the ITERACION to set
     */
    public void setITERACION(Integer ITERACION) {
        this.ITERACION = ITERACION;
    }

}
