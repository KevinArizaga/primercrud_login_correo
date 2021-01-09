package com.primercrud.uabc.Primercrud.Controller;

import com.primercrud.uabc.Primercrud.Entity.*;
import com.primercrud.uabc.Primercrud.Enums.RolNombre;
import com.primercrud.uabc.Primercrud.Service.ConfirmationTokenService;
import com.primercrud.uabc.Primercrud.Service.RolService;
import com.primercrud.uabc.Primercrud.Service.Implementation.PersonaServiceImplementation;
import com.primercrud.uabc.Primercrud.Service.UserService;
import com.primercrud.uabc.Primercrud.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;


@Controller
@RequestMapping(path = "/university")
public class GeneralController {

    ConfirmationTokenService confirmationTokenService;

    @Autowired
    UserService userService;
    @Autowired
    PersonaServiceImplementation service;
    private Persona persona;

    @GetMapping(value = "/index")
    public String showPersonal(Model model) {
        model.addAttribute("personas", service.getAllPersonas());
        return "university/index";
    }

    @GetMapping(value = "/delete/{matricula}")
    public String deletePersona(@PathVariable("matricula") Integer matricula, Model model) {
        model.addAttribute("personas", service.deletePersona(matricula));
        if (service.deletePersona(matricula)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }

    @GetMapping(value = "/edit/{matricula}")
    public String editPersona(@PathVariable("matricula") Integer matricula, Model model) {
        if (service.existPersona(matricula)) {
            //En las condiciones de abajo verifico si el objeto guardado es una instancia de alguno
            //de las tres entidades para redireccionarlos a sus respectivos paginas de editar.
            //Lo hice así más que nada por que quería aprovechar un solo método, y bueno... no solo métodos.
            //Tambien trate de hacer una pagina para t0do (un controller, un service, un personaService, etc.) Pero se me complico
            //usando la clase abstracta. No supe como castear e identificar de que instancia es la clase que recibe el metodo antes de recibirlo con el @ModelAttribute... :'c
            //Es decir, no me dejó poner @ModelAttribute Persona persona, por que es abstracto. Mi idea era poder recibirlo de esa manera y luego castearlo para guardarlo,
            //pero no funciono. Si lo solucionaba tenia la posibilidad de usar una pagina para t0do, y no tener que usar 3 paginas diferentes para modificar cada entidad :/
            if (service.searchPersona(matricula) instanceof Alumno) {
                Alumno alumno = (Alumno) service.searchPersona(matricula);
                alumno.setSalario(null);
                alumno.setTypePersona("Alumno");
                model.addAttribute("alumno", alumno);
                return "university/editAlumno";
            } else if (service.searchPersona(matricula) instanceof Profesor) {
                Profesor profesor = (Profesor) service.searchPersona(matricula);
                profesor.setTypePersona("Profesor");
                model.addAttribute("profesor", profesor);
                return "university/editProfesor";
            } else if (service.searchPersona(matricula) instanceof Intendente) {
                Intendente intendente = (Intendente) service.searchPersona(matricula);
                intendente.setCourse(null);
                intendente.setTypePersona("Intendente");
                model.addAttribute("intendente", intendente);
                return "university/editIntendente";
            }
        }
        return "redirect:/university/index";
    }

    @GetMapping(value = "/registro")
    public String newUser(User user) {
        return "registro";
    }

    @PostMapping(value = "/registro")
    public String newUsero(User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    @GetMapping("/registro/confirm")
    String confirmMail(@RequestParam("token") String token) {
        userService.confirmUser(token);
        return "redirect:/login";
    }

    @PostMapping(value = "/edit/{matricula}")
    public String editPersona(@ModelAttribute Alumno alumno, @PathVariable("matricula") Integer matricula) {
        if (service.savePersona(alumno)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }
}
