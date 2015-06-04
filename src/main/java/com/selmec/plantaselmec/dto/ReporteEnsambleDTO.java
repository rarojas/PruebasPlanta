package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.vo.PruebaVo;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author HABIL8989
 */
public class ReporteEnsambleDTO {

    private String folio;
    private String fechaReporte;
    private String peModelo;
    private String peNoSerie;
    private String peNoOp;
    private String mMarcar;
    private String mModelo;
    private String mNoSerie;
    private String gMarca;
    private String gModelo;
    private String gNoSerie;
    private String kw;
    private String kva;
    private String tipoControl;
    private String radiador;
    private String cInterruptor;
    private String combustible;
    private String noFases;
    private String patin;
    private String vPrueba;
    private String frecuencia;
    private String altPrueba;
    private String guardas;
    private String realizo;
    private String superviso;
    private String fecha;
    private String rutaReporte;
    private String ejecutor;
    private String fProgramada;
    private String fReal;
    private String estatusPlanta;
    private String qr;

    private List<PruebaDTO> pruebaDTOs;
    private List<PruebaVo> sinCarga = new ArrayList();
    private List<PruebaVo> conCarga = new ArrayList();
    private List<PruebaVo> cargaSubita = new ArrayList();
    private List<PruebaVo> pruebasDeControl = new ArrayList();

    /**
     * ***Setters Getter
     *
     * @return s*****
     */
    public String getPeModelo() {
        return peModelo;
    }

    public void setPeModelo(String peModelo) {
        this.peModelo = peModelo;
    }

    public String getPeNoSerie() {
        return peNoSerie;
    }

    public void setPeNoSerie(String peNoSerie) {
        this.peNoSerie = peNoSerie;
    }

    public String getPeNoOp() {
        return peNoOp;
    }

    public void setPeNoOp(String peNoOp) {
        this.peNoOp = peNoOp;
    }

    public String getmMarcar() {
        return mMarcar;
    }

    public void setmMarcar(String mMarcar) {
        this.mMarcar = mMarcar;
    }

    public String getmModelo() {
        return mModelo;
    }

    public void setmModelo(String mModelo) {
        this.mModelo = mModelo;
    }

    public String getmNoSerie() {
        return mNoSerie;
    }

    public void setmNoSerie(String mNoSerie) {
        this.mNoSerie = mNoSerie;
    }

    public String getgMarca() {
        return gMarca;
    }

    public void setgMarca(String gMarca) {
        this.gMarca = gMarca;
    }

    public String getgModelo() {
        return gModelo;
    }

    public void setgModelo(String gModelo) {
        this.gModelo = gModelo;
    }

    public String getgNoSerie() {
        return gNoSerie;
    }

    public void setgNoSerie(String gNoSerie) {
        this.gNoSerie = gNoSerie;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getKva() {
        return kva;
    }

    public void setKva(String kva) {
        this.kva = kva;
    }

    public String getTipoControl() {
        return tipoControl;
    }

    public void setTipoControl(String tipoControl) {
        this.tipoControl = tipoControl;
    }

    public String getRadiador() {
        return radiador;
    }

    public void setRadiador(String radiador) {
        this.radiador = radiador;
    }

    public String getcInterruptor() {
        return cInterruptor;
    }

    public void setcInterruptor(String cInterruptor) {
        this.cInterruptor = cInterruptor;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getNoFases() {
        return noFases;
    }

    public void setNoFases(String noFases) {
        this.noFases = noFases;
    }

    public String getPatin() {
        return patin;
    }

    public void setPatin(String patin) {
        this.patin = patin;
    }

    public String getvPrueba() {
        return vPrueba;
    }

    public void setvPrueba(String vPrueba) {
        this.vPrueba = vPrueba;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getAltPrueba() {
        return altPrueba;
    }

    public void setAltPrueba(String altPrueba) {
        this.altPrueba = altPrueba;
    }

    public String getGuardas() {
        return guardas;
    }

    public void setGuardas(String guardas) {
        this.guardas = guardas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getRealizo() {
        return realizo;
    }

    public void setRealizo(String realizo) {
        this.realizo = realizo;
    }

    public String getSuperviso() {
        return superviso;
    }

    public void setSuperviso(String superviso) {
        this.superviso = superviso;
    }

    public JRDataSource getPruebas() {
        return new JRBeanCollectionDataSource(pruebaDTOs);
    }

    public JRDataSource getSinCarga() {
        return new JRBeanCollectionDataSource(sinCarga);
    }

    public JRDataSource getConCarga() {
        return new JRBeanCollectionDataSource(conCarga);
    }

    public JRDataSource getCargaSubita() {
        return new JRBeanCollectionDataSource(cargaSubita);
    }

    public JRDataSource getPruebasDeControl() {
        return new JRBeanCollectionDataSource(pruebasDeControl);
    }

    public List<PruebaDTO> getPruebaDTOs() {
        return pruebaDTOs;
    }

    public void setPruebaDTOs(List<PruebaDTO> pruebaDTOs) {
        this.pruebaDTOs = pruebaDTOs;
    }

    public void setSinCarga(List<PruebaVo> sinCarga) {
        this.sinCarga = sinCarga;
    }

    public void setConCarga(List<PruebaVo> conCarga) {
        this.conCarga = conCarga;
    }

    public void setCargaSubita(List<PruebaVo> cargaSubita) {
        this.cargaSubita = cargaSubita;
    }

    public void setPruebasDeControl(List<PruebaVo> pruebasDeControl) {
        this.pruebasDeControl = pruebasDeControl;
    }

    public void addSinCarga(PruebaVo pruebaVo) {
        sinCarga.add(pruebaVo);
    }

    public void addConCarga(PruebaVo pruebaVo) {
        conCarga.add(pruebaVo);
    }

    public void addCargaSubita(PruebaVo pruebaVo) {
        cargaSubita.add(pruebaVo);
    }

    public void addPruebasDeControl(PruebaVo pruebaVo) {
        pruebasDeControl.add(pruebaVo);
    }

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }

    public String getfProgramada() {
        return fProgramada;
    }

    public void setfProgramada(String fProgramada) {
        this.fProgramada = fProgramada;
    }

    public String getfReal() {
        return fReal;
    }

    public void setfReal(String fReal) {
        this.fReal = fReal;
    }

    public String getEstatusPlanta() {
        return estatusPlanta;
    }

    public void setEstatusPlanta(String estatusPlanta) {
        this.estatusPlanta = estatusPlanta;
    }

    public String getRutaReporte() {
        return rutaReporte;
    }

    public void setRutaReporte(String rutaReporte) {
        this.rutaReporte = rutaReporte;
    }

    /**
     * @return the qr
     */
    public String getQr() {
        return qr;
    }

    /**
     * @param qr the qr to set
     */
    public void setQr(String qr) {
        this.qr = qr;
    }

    /**
     * ***Setters Getters*****
     */
}
