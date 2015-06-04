package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.dto.GanttEnsamble;
import com.selmec.plantaselmec.services.IPlaneacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("/api/GanttEnsamble")
public class GanttEnsambleController {

    IPlaneacionService planeacionService;

    @Autowired
    public void setPlaneacionService(IPlaneacionService planeacionService) {
        this.planeacionService = planeacionService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<GanttEnsamble> Planeacion() {
        return planeacionService.GetPlaneacion();
    }

    @ResponseBody
    @RequestMapping(value = "Arranques", method = RequestMethod.GET)
    public List<GanttEnsamble> Arranques() {
        return planeacionService.GetArranques();
    }
}
