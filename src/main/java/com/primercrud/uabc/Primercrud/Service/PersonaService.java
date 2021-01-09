package com.primercrud.uabc.Primercrud.Service;

import com.primercrud.uabc.Primercrud.Entity.Persona;

import java.util.List;

public interface PersonaService {

    public boolean savePersona(Persona persona);

    public boolean editPersona(Persona persona);

    public boolean deletePersona(Integer id);

    public boolean existPersona(Integer id);

    public Persona searchPersona(Integer id);

    public List<Persona> getAllPersonas();

    public List getAllTypePersonas();
}
