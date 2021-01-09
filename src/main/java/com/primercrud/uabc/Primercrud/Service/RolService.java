package com.primercrud.uabc.Primercrud.Service;

import com.primercrud.uabc.Primercrud.Entity.Rol;
import com.primercrud.uabc.Primercrud.Enums.RolNombre;
import com.primercrud.uabc.Primercrud.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre) {
        return rolRepository.existsByRolNombre(rolNombre);
    }
}
