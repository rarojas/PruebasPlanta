/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ricardo
 */
@Service
public class ExporterReport  implements IExporterReport {

    private final Logger logger = Logger.getLogger(ExporterReport.class);

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public String encodeToString(String file) throws FileNotFoundException, IOException {
        BufferedImage image = ImageIO.read(new File(file));
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
   

    @Override
    public byte[] ExportToStream(JasperPrint jasperPrint) throws JRException {
        logger.info("loading... jasper !!");
        byte[] report;
        report = JasperExportManager.exportReportToPdf(jasperPrint);
        logger.info("Export Report Successful!!");
        return report;
    }
}
