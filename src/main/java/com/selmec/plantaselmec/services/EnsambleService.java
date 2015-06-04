package com.selmec.plantaselmec.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.EstadoEnsamble;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.sql.DataSource;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
public class EnsambleService extends BaseServices<Ensamble, Integer> implements IEnsambleService {

    private final Logger logger = Logger.getLogger(EnsambleService.class);

    @Autowired
    DataSource dataSource;

    @Qualifier("jdbctemplateMySql")
    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    public void setDao(IGenericDao<Ensamble, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Ensamble.class);
    }

    @Transactional
    @Override
    public List<Ensamble> GetByUser(Usuarios usuarios) {
        if (usuarios.HasAnyRole("Administrador,Supervisor Pruebas Ensamble")) {
            return dao.getCurrentSession().createCriteria(Ensamble.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
        return dao.getCurrentSession().createCriteria(Ensamble.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("usuarios.id", usuarios.getId())).list();
    }

    @Override
    public void ExcuteSPControl(int estado) {
        String queryString;
        queryString = String.format("EXECUTE PROCEDURE sp_control(%s,'CTE0000003','UBC0000004','EQ00000081')", estado);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("EXECUTE PROCEDURE sp_control(%s,'CTE0000003','UBC0000004,'EQ00000081') execute successfully");
    }

    @Override
    public void TurnOnCarril(int estado) {
        String queryString;
        Ensamble prueba = (Ensamble) dao.getCurrentSession().get(Ensamble.class, estado);
        String equipo = prueba.getCariles().getPlanta();
        queryString = String.format(
                "update  tablaescritura  set  tagvalue = 510 where  variable = 'CONTROL1' AND IDEQUIPO = '%s';"
                + "update tablaescritura set tagvalue = 1 where variable = 'MODO CONTROL' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 0 where  variable = 'CONTROL2' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 1 where  variable = 'CONTROL3' AND IDEQUIPO = '%s';", equipo, equipo, equipo, equipo);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("Record inserted successfully");
    }

    @Override
    public void TurnOffCarril(int estado) {
        String queryString;
        Ensamble prueba = (Ensamble) dao.getCurrentSession().get(Ensamble.class, estado);
        String equipo = prueba.getCariles().getPlanta();
        queryString = String.format(
                "update tablaescritura set tagvalue = 1 where variable = 'MODO CONTROL' AND IDEQUIPO = '%s';"  
                + "update  tablaescritura set  tagvalue = 765 where  variable = 'CONTROL1' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 0 where  variable = 'CONTROL2' AND IDEQUIPO = '%s';"
                + "update  tablaescritura set  tagvalue = 1 where  variable = 'CONTROL3' AND IDEQUIPO = '%s';", equipo, equipo, equipo, equipo);
        logger.info(estado);
        logger.info(queryString);
        this.jdbctemplate = new JdbcTemplate(dataSource);
        jdbctemplate.update(queryString);
        logger.info("Record inserted successfully");
    }

    @Transactional
    @Override
    public void Rechazar(int id, Usuarios usuario) {
        Ensamble ensamble = dao.findOne(id);
        ensamble.setAutorizo(usuario);
        ensamble.setEstatus(EstadoEnsamble.Rechazada);
        ensamble.setDtAutorizacion(new Date());
    }

    @Transactional
    @Override
    public void Aprobar(int id, Usuarios usuario) {
        Ensamble ensamble = dao.findOne(id);
        ensamble.setDtAutorizacion(new Date());
        ensamble.setAutorizo(usuario);
        ensamble.setEstatus(EstadoEnsamble.Aprobada);
    }

    @Transactional(readOnly = true)
    @Override
    public String GenerateQR(int id, String appPath) {
        try {
            String filePath = "/QR%s.png";
            int size = 125;
            String fileType = "png";
            String myCodeText = "";
            Ensamble ensamble = dao.findOne(id);
            filePath = String.format(filePath, ensamble.getId());
            myCodeText = String.format("%s|%s", ensamble.getId(), ensamble.getFolio());
            File myFile = new File(appPath + filePath);

            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap;
            hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix;

            byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
            return myFile.getName();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EnsambleService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriterException ex) {
            java.util.logging.Logger.getLogger(EnsambleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
