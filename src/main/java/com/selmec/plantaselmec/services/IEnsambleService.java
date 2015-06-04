/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Ensamble;
import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.utils.services.IBaseServices;
import java.util.List;

/**
 *
 * @author rrojase
 */
public interface IEnsambleService extends IBaseServices<Ensamble, Integer> {

    void TurnOnCarril(int estado);

    void TurnOffCarril(int estado);

    void ExcuteSPControl(int estado);

    List<Ensamble> GetByUser(Usuarios usuarios);

    void Rechazar(int id, Usuarios usuario);

    void Aprobar(int id, Usuarios usuario);

    String GenerateQR(int id, String appPath);
}
