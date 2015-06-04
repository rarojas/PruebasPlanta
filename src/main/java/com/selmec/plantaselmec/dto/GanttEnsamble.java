/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author rrojase
 */
public class GanttEnsamble {

    public UUID id;
    public String name;    
    public List<Task> tasks = new ArrayList();

}
