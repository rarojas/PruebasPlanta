/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ricardo
 */
public interface IExporterReport {

    public String encodeToString(String file) throws FileNotFoundException, IOException;

    public byte[] ExportToStream(JasperPrint jasperPrint) throws JRException;
}
