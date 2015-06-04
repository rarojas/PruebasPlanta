    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cargasubita;
import com.selmec.plantaselmec.Models.EstadoPrueba;
import com.selmec.plantaselmec.Models.Lecturas;
import com.selmec.plantaselmec.Models.Prueba;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.CargasubitaDTO;
import com.selmec.plantaselmec.dto.LecturaDTO;
import com.selmec.plantaselmec.dto.LecturasDTO;
import com.selmec.utils.dao.IGenericDao;
import com.selmec.utils.services.BaseServices;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author GEID@R
 */
@Service
public class PruebaServices extends BaseServices<Prueba, Integer> implements IPruebaServices {

    private Logger logger = Logger.getLogger(PruebaServices.class);

    @Autowired
    IUsuariosServices usuariosServices;

    @Autowired
    public void setDao(IGenericDao< Prueba, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Prueba.class);
    }

    @Transactional
    @Override
    public List<Prueba> GetByUser(Usuarios usuarios) {
        if (usuarios.HasAnyRole("Administrador,Supervisor")) {
            return dao.getCurrentSession().createCriteria(Prueba.class).list();
        }
        return dao.getCurrentSession().createCriteria(Prueba.class)
                .add(Restrictions.eq("ensamble.usuarios.id", usuarios.getId())).list();
    }

    @Transactional
    @Override
    public String CarrilByPrueba(int id) {
        return dao.findOne(id).getEnsamble().getCariles().getEquipo();
    }

    @Transactional
    @Override
    public void cambioEstatusPrueba(int id, String nombreUsuario, EstadoPrueba estatus) {

        Prueba prueba = (Prueba) dao.getCurrentSession().get(Prueba.class, id);
        Usuarios usuario = usuariosServices.GetByUsername(nombreUsuario);
        prueba.setAprueba(usuario.getId());
        Date today = Calendar.getInstance().getTime();
        prueba.setDtAprueba(today);
        prueba.setEstatus(estatus);
        dao.getCurrentSession().merge(prueba);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LecturaDTO> Lecturas(@PathVariable("id") int id) {
        logger.info("Inicio");         
        List<LecturaDTO> result = mapper.mapAsList(dao.getCurrentSession()
                .createCriteria(Lecturas.class)
                .add(Restrictions.eq("pruebabase.id", id))
                .addOrder(Order.asc("segundo")).list(), LecturaDTO.class);
        logger.info("Fin");
        return result;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<LecturasDTO> Lectura(@PathVariable("id") int id) {
        logger.info("Inicio");         
        List<LecturasDTO> result = mapper.mapAsList(dao.getCurrentSession()
                .createCriteria(Lecturas.class)
                .add(Restrictions.eq("pruebabase.id", id))
                .addOrder(Order.asc("segundo")).list(), LecturasDTO.class);
        logger.info("Fin");
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<LecturaDTO> LecturasSummary(@PathVariable("id") int id) {
        String sql = "select cast((segundo/10) as  SIGNED) \"segundo\",\n"
                + "avg(l1)  as l1,avg(l2) as l2,avg(l3) as l3,\n"
                + "avg(i1)  as i1,avg(i2) as i2,avg(i3) as i3,\n"
                + "avg(temp) as temp,avg(presion) as presion,avg(hz) as hz,\n"
                + "avg(bateria) as bateria,avg(RMP) as RMP,avg(iteracion) as iteracion,\n"
                + "avg(l1l2) as l1l2,avg(l2l3) as l2l3,avg(l3l1) as l3l1, max(id) as id\n"
                + "from  casetapruebas.lecturas\n"
                + "where pruebaid = " + id
                + " group by cast((segundo/10) as  SIGNED) ";
        List<Lecturas> listaLecturas = jdbctemplate.query(sql, new BeanPropertyRowMapper(Lecturas.class));
        List<LecturaDTO> result = mapper.mapAsList(listaLecturas, LecturaDTO.class);
        return result;
    }

    @Autowired
    MapperFacade mapper;

    @Qualifier("jdbctemplateMySql")
    @Autowired
    private JdbcTemplate jdbctemplate;

    @Transactional(readOnly = true)
    @Override
    public List<LecturaDTO> LecturasCC(@PathVariable("id") int id) {
        logger.debug("Inicio");
        String sql = " select lecturas.ID, lecturas.L1L2, lecturas.L2L3, lecturas.L3L1, lecturas.HZ, lecturas.TEMP, lecturas.L1, "
                + "       lecturas.L2, lecturas.L3, lecturas.dtStamp, lecturas.PruebaID, lecturas.RMP, lecturas.I1, lecturas.I2,"
                + "       lecturas.I3, lecturas.presion, lecturas.bateria, lecturas.segundo, lecturas.iteracion"
                + " from "
                + " ("
                + "	select lecturasAux1.* from lecturas lecturasAux1 "
                + "	where lecturasAux1.PruebaID = '" + id + "'"
                + "	and lecturasAux1.segundo IN ("
                + "		select	max(LecturasAux1_1.segundo) "
                + "		from lecturas LecturasAux1_1 "
                + "		where LecturasAux1_1.PruebaID = '" + id + "'"
                + "		and exists 	("
                + "				select distinct(LecturasAux1_2.iteracion) from lecturas LecturasAux1_2 where LecturasAux1_2.PruebaId = '" + id + "'"
                + "				)"
                + "		group by LecturasAux1_1.iteracion"
                + "	)"
                + "	order by lecturasAux1.segundo"
                + " ) lecturas "
                + " order by iteracion";

        List<Lecturas> listaLecturas = jdbctemplate.query(sql, new BeanPropertyRowMapper(Lecturas.class));
        List<LecturaDTO> result = mapper.mapAsList(listaLecturas, LecturaDTO.class);
        logger.debug("Fin");
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CargasubitaDTO> recuperarInfoCargaSubita(@PathVariable("id") int id) throws Exception {
        logger.debug("Inicio");
        List<CargasubitaDTO> result = null;
        String msjEx;
        try {
            List<Cargasubita> resultAux = dao.getCurrentSession().createCriteria(Cargasubita.class)
                    .add(Restrictions.eq("prueba.id", id)).addOrder(Order.asc("id")).list();
            if(resultAux.isEmpty())
                return new ArrayList<>();
            result = mapper.mapAsList(resultAux, CargasubitaDTO.class);
            logger.info("result.size(): " + result.size());

        } catch (Exception ex) {
            msjEx = "Ocurrio un error al: recuperar la informacion de la carga subita para el identificador: " + id + ".";
            throw new Exception(msjEx, ex);
        }

        logger.debug("Fin");
        return result;
    }

}
