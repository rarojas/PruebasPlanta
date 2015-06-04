/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Lecturas;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author rrojase
 */
public class LecturaDTO {

    public String L1L2;
    public String L2L3;
    public String L3L1;

    public Integer x;
    public String L1N;
    public String L2N;
    public String L3N;
    public String Temp;
    public String HZ;
    public String I1;
    public String I2;
    public String I3;
    public String iTotal;
    public String Presion;
    public String Bateria;
    public Integer Iteracion;

    public LecturaDTO() {
    }

    public LecturaDTO(Lecturas lectura) {
        DecimalFormat formatoDosDecimales = new DecimalFormat("##0.00");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        formatoDosDecimales.setDecimalFormatSymbols(simbolos);
        if (lectura.getL1() != null) {
            this.L1N = formatoDosDecimales.format(lectura.getL1());
        } else {
            this.L1N = "0.00";
        }
        if (lectura.getL2() != null) {
            this.L2N = formatoDosDecimales.format(lectura.getL2());
        } else {
            this.L2N = "0.00";
        }
        if (lectura.getL3() != null) {
            this.L3N = formatoDosDecimales.format(lectura.getL3());
        } else {
            this.L3N = "0.00";
        }

        if (lectura.getTemp() != null) {
            this.Temp = formatoDosDecimales.format(lectura.getTemp());
        } else {
            this.Temp = "0.00";
        }
        if (lectura.getHz() != null) {
            this.HZ = formatoDosDecimales.format(lectura.getHz());
        } else {
            this.HZ = "0.00";
        }

        this.x = lectura.getSegundo();

        Double corrienteTotal = 0D;
        Boolean contieneCorriente = Boolean.FALSE;
        if (lectura.getI1() != null) {
            this.I1 = formatoDosDecimales.format(lectura.getI1());
            corrienteTotal += lectura.getI1();
            contieneCorriente = Boolean.TRUE;
        } else {
            this.I1 = "0.00";
        }
        if (lectura.getI2() != null) {
            this.I2 = formatoDosDecimales.format(lectura.getI2());
            corrienteTotal += lectura.getI2();
            contieneCorriente = Boolean.TRUE;
        } else {
            this.I2 = "0.00";
        }
        if (lectura.getI3() != null) {
            this.I3 = formatoDosDecimales.format(lectura.getI3());
            corrienteTotal += lectura.getI3();
            contieneCorriente = Boolean.TRUE;
        } else {
            this.I3 = "0.00";
        }

        if (contieneCorriente) {
            this.iTotal = formatoDosDecimales.format(corrienteTotal);
        } else {
            this.iTotal = "0.00";
        }

        if (lectura.getL1l2() != null) {
            this.L1L2 = formatoDosDecimales.format(lectura.getL1l2());
        } else {
            this.L1L2 = "0.00";
        }
        if (lectura.getL2l3() != null) {
            this.L2L3 = formatoDosDecimales.format(lectura.getL2l3());
        } else {
            this.L2L3 = "0.00";
        }
        if (lectura.getL3l1() != null) {
            this.L3L1 = formatoDosDecimales.format(lectura.getL3l1());
        } else {
            this.L3L1 = "0.00";
        }

        if (lectura.getPresion() != null) {
            this.Presion = formatoDosDecimales.format(lectura.getPresion());
        } else {
            this.Presion = "0.00";
        }

        if (lectura.getBateria() != null) {
            this.Bateria = formatoDosDecimales.format(lectura.getBateria());
        } else {
            this.Bateria = "0.00";
        }

        this.Iteracion = lectura.getIteracion();
    }

    public String getL1l2() {
        return L1L2;
    }

    public void setL1L2(String L1L2) {
        this.L1L2 = L1L2;
    }

    public String getL2l3() {
        return L2L3;
    }

    public void setL2L3(String L2L3) {
        this.L2L3 = L2L3;
    }

    public String getL3l1() {
        return L3L1;
    }

    public void setL3L1(String L3L1) {
        this.L3L1 = L3L1;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public String getL1n() {
        return L1N;
    }

    public void setL1N(String L1N) {
        this.L1N = L1N;
    }

    public String getL2n() {
        return L2N;
    }

    public void setL2N(String L2N) {
        this.L2N = L2N;
    }

    public String getL3n() {
        return L3N;
    }

    public void setL3n(String L3N) {
        this.L3N = L3N;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String Temp) {
        this.Temp = Temp;
    }

    public String getHz() {
        return HZ;
    }

    public void setHZ(String HZ) {
        this.HZ = HZ;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1 = I1;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2 = I2;
    }

    public String getI3() {
        return I3;
    }

    public void setI3(String I3) {
        this.I3 = I3;
    }

    public String getiTotal() {
        return iTotal;
    }

    public void setiTotal(String iTotal) {
        this.iTotal = iTotal;
    }

    public String getPresion() {
        return Presion;
    }

    public void setPresion(String Presion) {
        this.Presion = Presion;
    }

    public String getBateria() {
        return Bateria;
    }

    public void setBateria(String Bateria) {
        this.Bateria = Bateria;
    }

    public Integer getIteracion() {
        return Iteracion;
    }

    public void setIteracion(Integer Iteracion) {
        this.Iteracion = Iteracion;
    }

}
