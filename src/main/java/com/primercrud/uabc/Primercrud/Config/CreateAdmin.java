package com.primercrud.uabc.Primercrud.Config;

import com.primercrud.uabc.Primercrud.Entity.Rol;
import com.primercrud.uabc.Primercrud.Entity.Usuario;
import com.primercrud.uabc.Primercrud.Enums.RolNombre;
import com.primercrud.uabc.Primercrud.Service.RolService;
import com.primercrud.uabc.Primercrud.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        if (!rolService.existsByRolNombre(RolNombre.ROLE_ADMIN)) {
            Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
            Rol rolUser = new Rol(RolNombre.ROLE_USER);
            rolService.save(rolAdmin);
            rolService.save(rolUser);
            Usuario usuario = new Usuario();
            String passwordEncoded = passwordEncoder.encode("admin");
            usuario.setNombreUsuario("admin");
            usuario.setPassword(passwordEncoded);
            rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
            rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
            Set<Rol> roles = new HashSet<>();
            roles.add(rolAdmin);
            roles.add(rolUser);
            usuario.setRoles(roles);
            usuarioService.save(usuario);
        }
    }
}
