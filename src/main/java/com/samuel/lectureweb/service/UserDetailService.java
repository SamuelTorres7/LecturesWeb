package com.samuel.lectureweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa la interfaz UserDetailsService para cargar detalles de usuario.
 * 
 * Este servicio se encarga de recuperar la información del usuario a partir de su nombre de usuario.
 * 
 * @author Edrehy Torres
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Carga un usuario específico por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario a cargar
     * @return un objeto UserDetails que contiene la información del usuario
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }
}
