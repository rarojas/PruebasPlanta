package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.selmec.plantaselmec.Models.Reports.TipoConexion;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Planta generated by hbm2java
 */
@Entity
@Table(name = "planta", catalog = "casetapruebas"
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planta implements java.io.Serializable {

    private String noSerie;
    private Motores motores;
    private String noOp;
    private String motorSerie;
    private String controlId;
    private int tipoOperacion;
    private String generadorSerie;
    private Integer voltaje;
    private Integer voltajeFases;
    private Integer capInt;
    private String modelo;
    private TipoConexion tipoConexion;
    private Set ensamblebases = new HashSet(0);
    private Integer frecuencia;
    private Integer frecHi;
    private Integer frecLow;
    private Integer vHi;
    private Integer vLow;
    private Double oilWrn;
    private Double oilSd;
    private Integer tempWrn;
    private Integer tempSd;

    public Planta() {
    }

    public Planta(String noSerie, Motores motores, String noOp, String motorSerie, String controlId, int tipoOperacion, String generadorSerie) {
        this.noSerie = noSerie;
        this.motores = motores;
        this.noOp = noOp;
        this.motorSerie = motorSerie;
        this.controlId = controlId;
        this.tipoOperacion = tipoOperacion;
        this.generadorSerie = generadorSerie;
    }

    public Planta(String noSerie, Motores motores, String noOp, String motorSerie, String controlId, int tipoOperacion, String generadorSerie, Integer voltaje, Integer capInt, Set ensamblebases) {
        this.noSerie = noSerie;
        this.motores = motores;
        this.noOp = noOp;
        this.motorSerie = motorSerie;
        this.controlId = controlId;
        this.tipoOperacion = tipoOperacion;
        this.generadorSerie = generadorSerie;
        this.voltaje = voltaje;
        this.capInt = capInt;
        this.ensamblebases = ensamblebases;
    }

    @Id

    @Column(name = "NoSerie", unique = true, nullable = false, length = 50)
    public String getNoSerie() {
        return this.noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MotorID", nullable = false)
    public Motores getMotores() {
        return this.motores;
    }

    public void setMotores(Motores motores) {
        this.motores = motores;
    }

    @Column(name = "NoOp", nullable = false, length = 50)
    public String getNoOp() {
        return this.noOp;
    }

    public void setNoOp(String noOp) {
        this.noOp = noOp;
    }

    @Column(name = "MotorSerie", nullable = false, length = 50)
    public String getMotorSerie() {
        return this.motorSerie;
    }

    public void setMotorSerie(String motorSerie) {
        this.motorSerie = motorSerie;
    }

    @Column(name = "ControlID", nullable = false, length = 50)
    public String getControlId() {
        return this.controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId;
    }

    @Column(name = "TipoOperacion", nullable = false)
    public int getTipoOperacion() {
        return this.tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    @Column(name = "GeneradorSerie", nullable = false, length = 45)
    public String getGeneradorSerie() {
        return this.generadorSerie;
    }

    public void setGeneradorSerie(String generadorSerie) {
        this.generadorSerie = generadorSerie;
    }

    @Column(name = "Voltaje")
    public Integer getVoltaje() {
        return this.voltaje;
    }

    public void setVoltaje(Integer voltaje) {
        this.voltaje = voltaje;
    }

    @Column(name = "CapInt")
    public Integer getCapInt() {
        return this.capInt;
    }

    public void setCapInt(Integer capInt) {
        this.capInt = capInt;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planta")
    public Set getEnsamblebases() {
        return this.ensamblebases;
    }

    public void setEnsamblebases(Set ensamblebases) {
        this.ensamblebases = ensamblebases;
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
     * @return the modelo
     */
    @Column(name = "modelo", length = 45, nullable = false)
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the voltajeFases
     */
    @Column(name = "voltajeFases")
    public Integer getVoltajeFases() {
        return voltajeFases;
    }

    /**
     * @param voltajeFases the voltajeFases to set
     */
    public void setVoltajeFases(Integer voltajeFases) {
        this.voltajeFases = voltajeFases;
    }

    /**
     * @return the frecuencia
     */
    public Integer getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * @return the frecHi
     */
    public Integer getFrecHi() {
        return frecHi;
    }

    /**
     * @param frecHi the frecHi to set
     */
    public void setFrecHi(Integer frecHi) {
        this.frecHi = frecHi;
    }

    /**
     * @return the frecLow
     */
    public Integer getFrecLow() {
        return frecLow;
    }

    /**
     * @param frecLow the frecLow to set
     */
    public void setFrecLow(Integer frecLow) {
        this.frecLow = frecLow;
    }

    /**
     * @return the vHi
     */
    public Integer getvHi() {
        return vHi;
    }

    /**
     * @param vHi the vHi to set
     */
    public void setvHi(Integer vHi) {
        this.vHi = vHi;
    }

    /**
     * @return the vLow
     */
    public Integer getvLow() {
        return vLow;
    }

    /**
     * @param vLow the vLow to set
     */
    public void setvLow(Integer vLow) {
        this.vLow = vLow;
    }

    /**
     * @return the oilWrn
     */
    public Double getOilWrn() {
        return oilWrn;
    }

    /**
     * @param oilWrn the oilWrn to set
     */
    public void setOilWrn(Double oilWrn) {
        this.oilWrn = oilWrn;
    }

    /**
     * @return the oilSd
     */
    public Double getOilSd() {
        return oilSd;
    }

    /**
     * @param oilSd the oilSd to set
     */
    public void setOilSd(Double oilSd) {
        this.oilSd = oilSd;
    }

    /**
     * @return the tempWrn
     */
    public Integer getTempWrn() {
        return tempWrn;
    }

    /**
     * @param tempWrn the tempWrn to set
     */
    public void setTempWrn(Integer tempWrn) {
        this.tempWrn = tempWrn;
    }

    /**
     * @return the tempSd
     */
    public Integer getTempSd() {
        return tempSd;
    }

    /**
     * @param tempSd the tempSd to set
     */
    public void setTempSd(Integer tempSd) {
        this.tempSd = tempSd;
    }

}
