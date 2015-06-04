/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.Models;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rrojase
 */
@Entity
@Table(name = "kit", catalog = "casetapruebas")
public class Kit extends Equipobase implements java.io.Serializable {

    private Set ensamblearranques;

    public Kit() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cariles")
    public Set getEnsambles() {
        return this.ensamblearranques;
    }

    public void setEnsambles(Set ensamblearranques) {
        this.ensamblearranques = ensamblearranques;
    }

}
