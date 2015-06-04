
package com.selmec.plantaselmec.dto;

/**
 *
 * @author HABIL8989
 */
public class ResumenPruebaDto {
    /***Planta Emergencia*****/
    private String peModelo;   
    private String peNoSerie;
    private String peNoOp;
    /****Motor****/
    private String mMarcar;
    private String mModelo;
    private String mNoSerie;
    /******Generador*********/
    private String gMarca;
    private String gModelo;
    private String gNoSerie;
    
    /*****Setters Getter
     * @return s******/
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
    /*****Setters Getters******/
}