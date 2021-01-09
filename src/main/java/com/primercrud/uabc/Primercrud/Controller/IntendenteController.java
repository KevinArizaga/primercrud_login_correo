package com.primercrud.uabc.Primercrud.Controller;

import com.primercrud.uabc.Primercrud.Entity.Alumno;
import com.primercrud.uabc.Primercrud.Entity.Intendente;
import com.primercrud.uabc.Primercrud.Service.Implementation.PersonaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/university/intendente")
public class IntendenteController {

    @Autowired
    PersonaServiceImplementation service;

    @GetMapping(value = "/add")
    public String addIntendente(Model model) {
        model.addAttribute("intendente", new Intendente());
        return "/university/addIntendente";
    }

    @PostMapping(value = "/add")
    public String addIntendente(@ModelAttribute Intendente intendente) {
        intendente.setTypePersona("Intendente");
        service.savePersona(intendente);
        return "redirect:/university/index";
    }

    @PostMapping(value = "/edit/{matricula}")
    public String editPersona(@ModelAttribute Intendente intendente, @PathVariable("matricula") Integer matricula) {
        intendente.setTypePersona("Intendente");
        if (service.savePersona(intendente)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }
}
