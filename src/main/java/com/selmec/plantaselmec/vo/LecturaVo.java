/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.vo;

import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author AAM
 * @author-email angel.aguilar@habilgroup.net
 */
public class LecturaVo {
    
    private String folio;
    private String fecha;
    private String inicio;
    private String fin;
    private String duracionPrueba;
    private String L1l2;
    private String L2l3;
    private String L3l1;
    private String x;
    private String L1n;
    private String L2n;
    private String L3n;
    private String Temp;
    private String Hz;
    private String I1;
    private String I2;
    private String I3;
    private String presion;
    private String bateria;
    private String iteracion;
    private String I1max;
    private String I2max;
    private String I3max;
    private String I1min;
    private String I2min;
    private String I3min;
    private String L1l2max;
    private String L2l3max;
    private String L3l1max;
    private String L1l2min;
    private String L2l3min;
    private String L3l1min;
    private String tempMax;
    private String hzMax;
    private String presionMax;
    private String tempMin;
    private String hzMin;
    private String presionMin;
    private String bateriaMax;
    private String bateriaMin;
    private String xCienCarga;
    private String rutaReporte;
    
    private String estatus;
    private String codIncidencia;
    private String comentarios;
    
    private List<LecturaDTO> lecturaDTOs;
    private List<LecturaDTO> lecturaDtoIteracion;
    private List<CargasubitaDTO> listaCargaSubitaVo;
       
    
            
    public String getL1l2() {
        return L1l2;
    }

    public void setL1l2(String L1l2) {
        this.L1l2 = L1l2;
    }

    public String getL2l3() {
        return L2l3;
    }

    public void setL2l3(String L2l3) {
        this.L2l3 = L2l3;
    }

    public String getL3l1() {
        return L3l1;
    }

    public void setL3l1(String L3l1) {
        this.L3l1 = L3l1;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getL1n() {
        return L1n;
    }

    public void setL1n(String L1n) {
        this.L1n = L1n;
    }

    public String getL2n() {
        return L2n;
    }

    public void setL2n(String L2n) {
        this.L2n = L2n;
    }

    public String getL3n() {
        return L3n;
    }

    public void setL3n(String L3n) {
        this.L3n = L3n;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String Temp) {
        this.Temp = Temp;
    }

    public String getHz() {
        return Hz;
    }

    public void setHz(String Hz) {
        this.Hz = Hz;
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

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getIteracion() {
        return iteracion;
    }

    public void setIteracion(String iteracion) {
        this.iteracion = iteracion;
    }

    public List<LecturaDTO> getLecturaDTOs() {
        return lecturaDTOs;
    }

    public void setLecturaDTOs(List<LecturaDTO> lecturaDTOs) {
        this.lecturaDTOs = lecturaDTOs;
    }
    
    public JRDataSource getLecturas(){       
        return new JRBeanCollectionDataSource(lecturaDTOs);   
    }
    
    public JRDataSource getLecturasIteracion(){       
        return new JRBeanCollectionDataSource(lecturaDtoIteracion);   
    }
     
    public JRDataSource getCargaSubita(){       
        return new JRBeanCollectionDataSource(listaCargaSubitaVo);   
    } 

    public List<CargasubitaDTO> getListaCargaSubitaVo() {
        return listaCargaSubitaVo;
    }

    public void setListaCargaSubitaVo(List<CargasubitaDTO> listaCargaSubitaVo) {
        this.listaCargaSubitaVo = listaCargaSubitaVo;
    }

    public String getI1max() {
        return I1max;
    }

    public void setI1max(String I1max) {
        this.I1max = I1max;
    }

    public String getI2max() {
        return I2max;
    }

    public void setI2max(String I2max) {
        this.I2max = I2max;
    }

    public String getI3max() {
        return I3max;
    }

    public void setI3max(String I3max) {
        this.I3max = I3max;
    }

    public String getI1min() {
        return I1min;
    }

    public void setI1min(String I1min) {
        this.I1min = I1min;
    }

    public String getI2min() {
        return I2min;
    }

    public void setI2min(String I2min) {
        this.I2min = I2min;
    }

    public String getI3min() {
        return I3min;
    }

    public void setI3min(String I3min) {
        this.I3min = I3min;
    }

    public String getL1l2max() {
        return L1l2max;
    }

    public void setL1l2max(String L1l2max) {
        this.L1l2max = L1l2max;
    }

    public String getL2l3max() {
        return L2l3max;
    }

    public void setL2l3max(String L2l3max) {
        this.L2l3max = L2l3max;
    }

    public String getL3l1max() {
        return L3l1max;
    }

    public void setL3l1max(String L3l1max) {
        this.L3l1max = L3l1max;
    }

    public String getL1l2min() {
        return L1l2min;
    }

    public void setL1l2min(String L1l2min) {
        this.L1l2min = L1l2min;
    }

    public String getL2l3min() {
        return L2l3min;
    }

    public void setL2l3min(String L2l3min) {
        this.L2l3min = L2l3min;
    }

    public String getL3l1min() {
        return L3l1min;
    }

    public void setL3l1min(String L3l1min) {
        this.L3l1min = L3l1min;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getHzMax() {
        return hzMax;
    }

    public void setHzMax(String hzMax) {
        this.hzMax = hzMax;
    }

    public String getPresionMax() {
        return presionMax;
    }

    public void setPresionMax(String presionMax) {
        this.presionMax = presionMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getHzMin() {
        return hzMin;
    }

    public void setHzMin(String hzMin) {
        this.hzMin = hzMin;
    }

    public String getPresionMin() {
        return presionMin;
    }

    public void setPresionMin(String presionMin) {
        this.presionMin = presionMin;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDuracionPrueba() {
        return duracionPrueba;
    }

    public void setDuracionPrueba(String duracionPrueba) {
        this.duracionPrueba = duracionPrueba;
    }

    public List<LecturaDTO> getLecturaDtoIteracion() {
        return lecturaDtoIteracion;
    }

    public void setLecturaDtoIteracion(List<LecturaDTO> lecturaDtoIteracion) {
        this.lecturaDtoIteracion = lecturaDtoIteracion;
    }

    public String getBateriaMax() {
        return bateriaMax;
    }

    public void setBateriaMax(String bateriaMax) {
        this.bateriaMax = bateriaMax;
    }

    public String getBateriaMin() {
        return bateriaMin;
    }

    public void setBateriaMin(String bateriaMin) {
        this.bateriaMin = bateriaMin;
    }

    public String getxCienCarga() {
        return xCienCarga;
    }

    public void setxCienCarga(String xCienCarga) {
        this.xCienCarga = xCienCarga;
    }

    public String getRutaReporte() {
        return rutaReporte;
    }

    public void setRutaReporte(String rutaReporte) {
        this.rutaReporte = rutaReporte;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodIncidencia() {
        return codIncidencia;
    }

    public void setCodIncidencia(String codIncidencia) {
        this.codIncidencia = codIncidencia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
 
}
