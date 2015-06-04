package com.selmec.plantaselmec.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rrojase
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "roles", catalog = "casetapruebas")
public class Rol implements Serializable {

    private int id;
    private String nbRol;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the rol
     */
    @Column(name = "rol", nullable = false, length = 50)
    public String getNbRol() {
        return nbRol;
    }

    /**
     * @param nbRol
     */
    public void setNbRol(String nbRol) {
        this.nbRol = nbRol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        Rol obj2 = (Rol) obj;
        if ((this.id == obj2.getId()) && (this.nbRol.equals(obj2.getNbRol()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        tmp = (id + nbRol).hashCode();
        return tmp;
    }

}
