package com.primercrud.uabc.Primercrud.Controller;

import com.primercrud.uabc.Primercrud.Entity.Alumno;
import com.primercrud.uabc.Primercrud.Entity.Profesor;
import com.primercrud.uabc.Primercrud.Service.Implementation.PersonaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/university/profesor")
public class ProfesorController {

    @Autowired
    PersonaServiceImplementation service;

    @GetMapping(value = "/add")
    public String addProfesor(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "/university/addProfesor";
    }

    @PostMapping(value = "/add")
    public String addProfesor(@ModelAttribute Profesor profesor) {
        profesor.setTypePersona("Profesor");
        service.savePersona(profesor);
        return "redirect:/university/index";
    }

    @PostMapping(value = "/edit/{matricula}")
    public String editPersona(@ModelAttribute Profesor profesor, @PathVariable("matricula") Integer matricula) {
        profesor.setTypePersona("Profesor");
        if (service.savePersona(profesor)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }
}
