package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Planta;
import com.selmec.plantaselmec.Models.Reports.TipoConexion;

public class PlantaDTO {

    public String noSerie;
    public MotorDTO motores;
    public String noOp;
    public String modelo;
    public String motorSerie;
    public String controlId;
    public int tipoOperacion;
    public String generadorSerie;
    public Integer capInt;
    public Integer voltaje;
    private Integer voltajeFases;
    private TipoConexion tipoConexion;

    public PlantaDTO() {
    }

    public PlantaDTO(Planta planta) {
        this.noSerie = planta.getNoSerie();
        if (planta.getMotores() != null) {
            this.motores = new MotorDTO(planta.getMotores());
        }
        this.controlId = planta.getControlId();
        this.noOp = planta.getNoOp();
        this.motorSerie = planta.getMotorSerie();
        this.modelo = planta.getModelo();
        this.tipoOperacion = planta.getTipoOperacion();
        this.capInt = planta.getCapInt();
        this.generadorSerie = planta.getGeneradorSerie();
        this.tipoOperacion = planta.getTipoOperacion();
        this.voltaje = planta.getVoltaje();
        this.tipoConexion = planta.getTipoConexion();
        this.voltajeFases = planta.getVoltajeFases();

    }

    /**
     * @return the tipoConexion
     */
    public TipoConexion getTipoConexion() {
        return tipoConexion;
    }

    /**
     * @param tipoConexion the tipoConexion to set
     */
    public void setTipoConexion(TipoConexion tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    /**
     * @return the voltajeFases
     */
    public Integer getVoltajeFases() {
        return voltajeFases;
    }

    /**
     * @param voltajeFases the voltajeFases to set
     */
    public void setVoltajeFases(Integer voltajeFases) {
        this.voltajeFases = voltajeFases;
    }
}
