package practica6.practica6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import practica6.practica6.services.RolService;
import practica6.practica6.services.UsuarioRolService;
import practica6.practica6.services.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService roleService;

    @Autowired
    private UsuarioRolService usuarioRolService;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        model.addAttribute("roles", roleService.obtenerRoles());
        model.addAttribute("usuariosRoles", usuarioRolService.obtenerUsuariosRoles());

        return "usuarios";  
    }
}
