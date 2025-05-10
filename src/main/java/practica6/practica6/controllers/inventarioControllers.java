package practica6.practica6.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import practica6.practica6.models.Rol;
import practica6.practica6.services.RolService;

@Controller
public class inventarioControllers {

    @Autowired
    private RolService rolService;

    @GetMapping("/roles/agregarUsuario")
    public String inventarioAgregarUsuario(Model model) {
        List<Rol> roles = rolService.obtenerRoles();
        model.addAttribute("roles", roles);
        return "/roles/agregarUsuario";
    }

    @PostMapping("/roles/agregarUsuario")
    public String insertarUsuario(@RequestParam("usuarioName") String usuarioName, Model model) {
        
        model.addAttribute("message", "Usuario agregado exitosamente");
        return "redirect:/roles/agregarUsuario";
    }

    @GetMapping("/roles/agregarRol")
    public String inventarioAgregarRol(Model model) {
        List<Rol> roles = rolService.obtenerRoles();
        model.addAttribute("roles", roles);
        return "/roles/agregarRol";
    }

    @PostMapping("/roles/agregarRol")
    public String insertarRol(@RequestParam("roleName") String roleName, Model model) {
        rolService.agregarRol(roleName);
        model.addAttribute("message", "Rol agregado exitosamente");
        return "redirect:/roles/agregarRol";
    }

    @GetMapping("/roles/editar/{id}")
    public String mostrarFormularioEditarRol(@PathVariable Long id, Model model) {
        Rol rol = rolService.obtenerRolPorId(id);
        model.addAttribute("rol", rol);
        return "/roles/editarRol";
    }

    @PostMapping("/roles/editar")
    public String actualizarRol(@RequestParam Long id, @RequestParam String roleName, Model model) {
        rolService.actualizarRol(id, roleName);
        return "redirect:/roles/agregarRol";
    }

    @GetMapping("/roles/eliminar/{id}")
    public String eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return "redirect:/roles/agregarRol";
    }
}
