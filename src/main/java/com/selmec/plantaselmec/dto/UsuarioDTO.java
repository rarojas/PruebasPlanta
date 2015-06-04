package com.selmec.plantaselmec.dto;

import com.selmec.plantaselmec.Models.Rol;
import com.selmec.plantaselmec.Models.Usuarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cognos
 */
public class UsuarioDTO implements java.io.Serializable {

    public String email;
    public String nombres;
    public String apellidos;
    public String password;
    public Integer id;
    public List<Rol> roles = new ArrayList<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuarios usuarios) {
        this.nombres = usuarios.getNombres();
        this.email = usuarios.getEmail();
        this.apellidos = usuarios.getApellidos();
        this.id = usuarios.getId();
        for (Object role : usuarios.getRoles()) {
            this.roles.add((Rol) role);
        }

    }

    @Override
    public String toString() {
        return String.format("%s %s", this.nombres, this.apellidos);
    }

}
