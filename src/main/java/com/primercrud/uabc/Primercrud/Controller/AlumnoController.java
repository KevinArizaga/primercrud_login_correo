package com.primercrud.uabc.Primercrud.Controller;

import com.primercrud.uabc.Primercrud.Entity.Alumno;
import com.primercrud.uabc.Primercrud.Entity.Profesor;
import com.primercrud.uabc.Primercrud.Service.Implementation.PersonaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/university/alumno")
public class AlumnoController {

    @Autowired
    PersonaServiceImplementation service;

    @GetMapping(value = "/add")
    public String addAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        return "/university/addAlumno";
    }

    @PostMapping(value = "/add")
    public String addAlumno(@ModelAttribute Alumno alumno) {
        alumno.setTypePersona("Alumno");
        service.savePersona(alumno);
        return "redirect:/university/index";
    }

    @PostMapping(value = "/edit/{matricula}")
    public String editPersona(@ModelAttribute Alumno alumno, @PathVariable("matricula") Integer matricula) {
        alumno.setTypePersona("Alumno");
        if (service.savePersona(alumno)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }
}
