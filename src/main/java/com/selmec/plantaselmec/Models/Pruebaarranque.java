package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pruebaarranque generated by hbm2java
 */
@Entity
@Table(name = "pruebaarranque", catalog = "casetapruebas"
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pruebaarranque extends Pruebabase implements java.io.Serializable {

    private Ensamblearranque ensamblearranque;
    private int tipo;
    private EstadoPruebaArranque estatus;
    private Integer aprueba;
    private Date dtAprueba;

    public Pruebaarranque() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EnsambleID", nullable = false)
    public Ensamblearranque getEnsamblearranque() {
        return this.ensamblearranque;
    }

    public void setEnsamblearranque(Ensamblearranque ensamblearranque) {
        this.ensamblearranque = ensamblearranque;
    }

    @Column(name = "Tipo", nullable = false)
    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Column(name = "Estatus", nullable = false)
    public EstadoPruebaArranque getEstatus() {
        return this.estatus;
    }

    public void setEstatus(EstadoPruebaArranque estatus) {
        this.estatus = estatus;
    }

    @Column(name = "Aprueba")
    public Integer getAprueba() {
        return this.aprueba;
    }

    public void setAprueba(Integer aprueba) {
        this.aprueba = aprueba;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dtAprueba", length = 19)
    public Date getDtAprueba() {
        return this.dtAprueba;
    }

    public void setDtAprueba(Date dtAprueba) {
        this.dtAprueba = dtAprueba;
    }

}
