package com.samuel.lectureweb.controller;

import com.samuel.lectureweb.domain.User;
import org.springframework.stereotype.Controller;
import com.samuel.lectureweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para manejar las operaciones relacionadas con la autenticación y gestión de usuarios.
 * Incluye funcionalidades para login, registro y configuración del usuario.
 *
 * @author Edrehy Torres
 */
@Controller
public class UserController {
    @Autowired
    private UserService usSer;
    
    /**
     * Muestra la página de inicio de sesión.
     * Si hay un error de autenticación, agrega un mensaje de error al modelo.
     *
     * @param error Parámetro opcional que indica si hubo un error de inicio de sesión.
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "login".
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Credenciales inválidas. Intenta nuevamente.");
        }
        return "login"; // Asegúrate de que este sea el nombre de tu vista de login
    }
    
    /**
     * Procesa la solicitud POST para registrar un nuevo usuario.
     * Guarda el nombre de usuario y la contraseña (cifrada) en la base de datos.
     *
     * @param username El nombre de usuario a registrar.
     * @param password La contraseña del usuario a registrar.
     * @return Una redirección a la página de login.
     */
    @PostMapping("/signup")
    public String signUpUs(@RequestParam String username, @RequestParam String password){
        User us = new User();
        us.setUsName(username);
        us.setUsPassw(password);
        usSer.saveCryptUser(us);
        return "redirect:/login";
    }
    
    /**
     * Muestra la página de registro de nuevos usuarios.
     *
     * @return La vista "signup".
     */
    @GetMapping("/signup")
    public String showSignUpPage(){
        return "signup";
    }
    
    /**
     * Muestra el panel de configuración del usuario.
     *
     * @return La vista "config".
     */
    @GetMapping("/config")
    public String showConfigPanel(){
        return "config";
    }
}