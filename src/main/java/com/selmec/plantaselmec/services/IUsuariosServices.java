package com.selmec.plantaselmec.services;

import com.selmec.plantaselmec.Models.Usuarios;
import com.selmec.plantaselmec.dto.ChangePassword;
import com.selmec.plantaselmec.dto.ResultBoolean;
import com.selmec.plantaselmec.dto.UsuarioActualDTO;
import com.selmec.plantaselmec.dto.UsuarioDTO;
import java.security.Principal;
import java.util.List;

public interface IUsuariosServices {

    public abstract Usuarios GetUsuario(int paramInt);

    public void Delete(int paramInt);

    void Update(UsuarioDTO usuario);

    List<Usuarios> All();    

    void Save(Usuarios paramUsuarios);

    public Usuarios GetByUsername(String paramString);

    List<UsuarioDTO> ToDTO(List<Usuarios> usuarios);

    UsuarioDTO ToDTO(Usuarios usuario);

    public UsuarioActualDTO getDataUser(String userName);

    public ResultBoolean changePassword(Principal current, ChangePassword obj);

}
