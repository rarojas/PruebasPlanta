/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author ricardo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePassword {

    public String newPassword;
    public String oldPassword;

}
