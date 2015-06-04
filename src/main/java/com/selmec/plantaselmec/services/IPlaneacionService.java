/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.dto.GanttEnsamble;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IPlaneacionService {

    List<GanttEnsamble> GetPlaneacion();

    List<GanttEnsamble> GetArranques();
}
