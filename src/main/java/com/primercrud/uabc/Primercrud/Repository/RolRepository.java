package com.primercrud.uabc.Primercrud.Repository;

import com.primercrud.uabc.Primercrud.Entity.Rol;
import com.primercrud.uabc.Primercrud.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    boolean existsByRolNombre(RolNombre rolNombre);
}
