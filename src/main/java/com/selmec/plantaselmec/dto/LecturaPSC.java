package com.selmec.plantaselmec.dto;

import com.selmec.Utils.Description;

public class LecturaPSC {

    public String Time;
    @Description("GENERADOR F1|VOLTAJE")
    //@Description("FASE 1|VOLTAJE|")
    public Double L1N;
    @Description("GENERADOR F2|VOLTAJE")
    //@Description("FASE 2|VOLTAJE|")
    public Double L2N;
    @Description("GENERADOR F3|VOLTAJE")
    //@Description("FASE 3|VOLTAJE|")
    public Double L3N;
    @Description("GENERADOR F1-F2|VOLTAJE")
    //@Description("FASE 1-FASE 2|VOLTAJE|")
    public Double L1L2;
    @Description("GENERADOR F2-F3|VOLTAJE")    
    //@Description("FASE 2-FASE 3|VOLTAJE|")
    public Double L2L3;
     @Description("GENERADOR F3-F1|VOLTAJE")
    //@Description("FASE 3-FASE 1|VOLTAJE|")
    public Double L3L1;
    @Description("GENERADOR|FRECUENCIA")
    //@Description("FRECUENCIA|FREQUENCY|")
    public Double HZ;
    //@Description("MOTOR|T REFRIGERANTE")
    @Description("MOTOR|TEMPERATURA")
    public Double Temp;
    @Description("MOTOR|PRESION ACEITE")
    public Double Presion;
    @Description("GENERADOR F1|CORRIENTE")
    //@Description("FASE 1|CORRIENTE|")
    public Double I1;
    @Description("GENERADOR F2|CORRIENTE")
    //@Description("FASE 2|CORRIENTE|")
    public Double I2;
    @Description("GENERADOR F3|CORRIENTE")
    //@Description("FASE 3|CORRIENTE|")
    public Double I3;
    @Description("GENERADOR|RPM")
    public Double RMP;
    @Description("GENERADOR|TIMER")
    public Double Timer;
    //@Description("MOTOR BATERIA|VOLTAJE") 
    @Description("MOTOR|VOLTAJE")
    public Double bateria;    
    @Description("GENERADOR|DEMANDA")
    public Double demanda;
    
    public int segundo;
    public int iteracion;   

}

