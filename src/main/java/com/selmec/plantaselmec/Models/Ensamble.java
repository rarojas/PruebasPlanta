package com.selmec.plantaselmec.Models;
// Generated Oct 30, 2014 5:43:43 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Ensamble generated by hbm2java
 */
@Entity
@Table(name = "ensamble", catalog = "casetapruebas"
)
public class Ensamble extends Ensamblebase implements java.io.Serializable {

    private int altitud;
    private String rediador;
    private String patin;    
    private Set pruebas = new HashSet(0);
    private Cariles cariles;
    private EstadoEnsamble Estatus;
    private Usuarios autorizo;
    private Date dtAutorizacion;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarrilID", nullable = false)
    public Cariles getCariles() {
        return this.cariles;
    }

    public void setCariles(Cariles cariles) {
        this.cariles = cariles;
    }

    @Column(name = "Altitud", nullable = false)
    public int getAltitud() {
        return this.altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    @Column(name = "Rediador", nullable = false, length = 50)
    public String getRediador() {
        return this.rediador;
    }

    public void setRediador(String rediador) {
        this.rediador = rediador;
    }

    @Column(name = "Patin", nullable = false, length = 50)
    public String getPatin() {
        return this.patin;
    }

    public void setPatin(String patin) {
        this.patin = patin;
    }
  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ensamble")
    public Set getPruebas() {
        return this.pruebas;
    }

    public void setPruebas(Set pruebas) {
        this.pruebas = pruebas;
    }

    public EstadoEnsamble getEstatus() {
        return Estatus;
    }

    public void setEstatus(EstadoEnsamble Estatus) {
        this.Estatus = Estatus;
    }
   
    @ManyToOne
    public Usuarios getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(Usuarios autorizo) {
        this.autorizo = autorizo;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDtAutorizacion() {
        return dtAutorizacion;
    }

    public void setDtAutorizacion(Date dtAutorizacion) {
        this.dtAutorizacion = dtAutorizacion;
    }

}
