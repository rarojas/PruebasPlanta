package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Cariles;
import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Ensamblearranque;
import com.selmec.plantaselmec.Models.Kit;
import com.selmec.plantaselmec.Models.Reports.ColorEnsamble;
import com.selmec.plantaselmec.dto.GanttEnsamble;
import com.selmec.plantaselmec.dto.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrojase
 */
@Service
@Transactional
public class PlaneacionService implements IPlaneacionService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<GanttEnsamble> GetPlaneacion() {
        List<Cariles> cariles = sessionFactory.getCurrentSession().createCriteria(Cariles.class).list();
        List<GanttEnsamble> result = new ArrayList<>();
        for (Cariles caril : cariles) {
            GanttEnsamble gantt = new GanttEnsamble();
            gantt.id = UUID.randomUUID();
            gantt.name = "Carril " + caril.getNoCarril();
            List<Ensamble> ensambles = sessionFactory.getCurrentSession().createCriteria(Ensamble.class).add(Restrictions.eq("cariles.id", caril.getId())).list();
            for (Ensamble ensamble : ensambles) {
                if (ensamble.getDtProgramada() != null) {
                    Task task = new Task();                    
                    task.color = ColorEnsamble.values()[ensamble.getEstatus().ordinal()].toString();
                    task.from = ensamble.getDtProgramada();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ensamble.getDtProgramada());
                    cal.add(Calendar.HOUR, 3);
                    task.to = cal.getTime();
                    task.name = "Planta " + ensamble.getPlanta().getNoOp();
                    task.id = UUID.randomUUID();
                    gantt.tasks.add(task);
                }
            }
            result.add(gantt);
        }
        return result;
    }

    @Override/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    public List<GanttEnsamble> GetArranques() {
        List<Kit> Kits = sessionFactory.getCurrentSession().createCriteria(Kit.class).list();
        List<GanttEnsamble> result = new ArrayList<>();
        for (Kit kit : Kits) {
            GanttEnsamble gantt = new GanttEnsamble();
            gantt.id = UUID.randomUUID();
            gantt.name = kit.getDescripcion();
            List<Ensamblearranque> ensambles = sessionFactory.getCurrentSession().createCriteria(Ensamblearranque.class).add(Restrictions.eq("kit.id", kit.getId())).list();
            for (Ensamblearranque ensamble : ensambles) {
                if (ensamble.getDtProgramada() != null) {
                    Task task = new Task();
                    task.color = "#67B331";
                    task.from = ensamble.getDtProgramada();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ensamble.getDtProgramada());
                    cal.add(Calendar.DATE, 1);
                    task.to = cal.getTime();
                    task.name = "Planta " + ensamble.getPlanta().getNoOp();
                    task.id = UUID.randomUUID();
                    gantt.tasks.add(task);
                }
            }
            result.add(gantt);
        }
        return result;
    }

}
