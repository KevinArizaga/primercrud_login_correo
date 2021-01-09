package com.primercrud.uabc.Primercrud.Service.Implementation;

import com.primercrud.uabc.Primercrud.Entity.Alumno;
import com.primercrud.uabc.Primercrud.Entity.Intendente;
import com.primercrud.uabc.Primercrud.Entity.Persona;
import com.primercrud.uabc.Primercrud.Entity.Profesor;
import com.primercrud.uabc.Primercrud.Repository.PersonaRepository;
import com.primercrud.uabc.Primercrud.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImplementation implements PersonaService {

    @Autowired
    PersonaRepository personaRepo;

    @Override
    public boolean savePersona(Persona persona) {
        try {
            personaRepo.save(persona);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //No es necesario el editPersona, con el savePersona(persona) automaticamente verifica si existe y lo remplaza.
    @Override
    public boolean editPersona(Persona persona) {
        return false;
    }

    @Override
    public boolean deletePersona(Integer id) {
        try {
            personaRepo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existPersona(Integer id) {
        return personaRepo.existsById(id);
    }

    @Override
    public Persona searchPersona(Integer id) {
        return personaRepo.findById(id).get();
    }

    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> allPersonas = new ArrayList<>();
        personaRepo.findAll().forEach(allPersonas::add);
        return allPersonas;
    }

    @Override
    public List getAllTypePersonas() {
        List allTypePersonas = new ArrayList<>();
        List<Persona> allPersonas = getAllPersonas();
        for (Integer k = 0; k < allPersonas.size(); k++) {
            if (allPersonas.get(k) instanceof Alumno) {
                allTypePersonas.add((Alumno) allPersonas.get(k));
            } else if (allPersonas.get(k) instanceof Profesor) {
                allTypePersonas.add((Profesor) allPersonas.get(k));
            } else if (allTypePersonas.get(k) instanceof Intendente) {
                allTypePersonas.add((Intendente) allPersonas.get(k));
            }
        }
        return allTypePersonas;
    }

}
