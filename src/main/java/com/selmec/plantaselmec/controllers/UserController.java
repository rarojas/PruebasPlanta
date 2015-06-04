package com.selmec.plantaselmec.controllers;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.ChangePassword;
import com.selmec.plantaselmec.dto.ResultBoolean;
import com.selmec.plantaselmec.dto.UsuarioActualDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import com.selmec.plantaselmec.services.IUsuariosServices;
import com.selmec.utils.dao.IGenericDao;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rrojase
 */
@Controller
@RequestMapping("api/Usuarios")
public class UserController extends BaseControllers<Usuarios, UsuarioDTO> {

    private IGenericDao<Usuarios, Integer> dao;

    @Autowired
    private IUsuariosServices usuariosServices;

    @Autowired
    public void setDao(IGenericDao< Usuarios, Integer> daoToSet) {
        dao = daoToSet;
        dao.setClazz(Usuarios.class);
    }

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UsuarioDTO> Get() {
        return DTO(usuariosServices.All(), Usuarios.class, UsuarioDTO.class);
    }

    @RequestMapping(value = "/Current", method = RequestMethod.GET)
    @ResponseBody
    public String Current(Principal principal) {
        return principal.getName();
    }

    @PreAuthorize("hasRole('Administrador')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public UsuarioDTO Get(@PathVariable("id") int id) {
        return new UsuarioDTO(usuariosServices.GetUsuario(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Usuarios Post(@RequestBody Usuarios usuarios) {
        usuariosServices.Save(usuarios);
        return usuarios;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void Put(@RequestBody UsuarioDTO usuarios) {
        usuariosServices.Update(usuarios);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void Delete(@PathVariable("id") int id) {
        usuariosServices.Delete(id);
    }

    @RequestMapping(value = "getDataUser", method = RequestMethod.GET)
    @ResponseBody
    public UsuarioActualDTO getDataUser(Principal principal) {
        return usuariosServices.getDataUser(principal.getName());
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultBoolean changePassword(Principal principal, @RequestBody ChangePassword obj) {
        return usuariosServices.changePassword(principal, obj);
    }

    @RequestMapping(value = "/SetPassword/{ticket}")
    public ModelAndView SetPasword(@PathVariable("ticket") UUID ticket) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Inicio/SetPassword");
        return mv;
    }

    
    @RequestMapping(value = "/SetPassword/{ticket}", method = RequestMethod.POST)
    public ModelAndView SetPaswordPost(@PathVariable("ticket") UUID ticket) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}
