package com.samuel.lectureweb.service;

import com.samuel.lectureweb.domain.User;
import com.samuel.lectureweb.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios.
 * 
 * Este servicio permite realizar operaciones como obtener todos los usuarios,
 * buscar usuarios por nombre o ID, guardar usuarios (con o sin encriptación),
 * y eliminar usuarios.
 * 
 * @author Edrehy Torres
 */
@Service
public class UserService {
    
    private final UserRepository usrepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
    /**
     * Constructor del servicio de usuario.
     *
     * @param usrepo el repositorio de usuarios utilizado para acceder a la base de datos
     */
    public UserService(UserRepository usrepo) {
        this.usrepo = usrepo;
    }
    
    /**
     * Obtiene todos los usuarios.
     *
     * @return una lista de todos los usuarios
     */
    public List<User> getAllBooks() {
        return usrepo.findAll();
    }
    
    /**
     * Busca un usuario por su nombre.
     *
     * @param name el nombre del usuario a buscar
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no
     */
    public Optional<User> getUserByName(String name) {
        List<User> users = usrepo.findAll();
        for (User us : users) {
            if (us.getUsName().equals(name)) {
                return getUserById(us.getUsId());
            }
        }
        return Optional.empty();
    }
    
    /**
     * Busca un usuario por su ID.
     *
     * @param id el ID del usuario a buscar
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no
     */
    public Optional<User> getUserById(int id) {
        return usrepo.findById(id);
    }
    
    /**
     * Guarda un usuario sin encriptar la contraseña.
     *
     * @param b el usuario a guardar
     * @return el usuario guardado
     */
    public User saveUser(User b) {
        usrepo.save(b);
        return b;
    }
    
    /**
     * Guarda un usuario encriptando su contraseña.
     *
     * @param u el usuario a guardar
     * @return true si el usuario se guardó correctamente
     */
    public boolean saveCryptUser(User u) {
        String rawPassword = u.getUsPassw();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        u.setUsPassw(encodedPassword);
        usrepo.save(u);
        return true;
    }
    
    /**
     * Elimina un usuario por su ID.
     *
     * @param id el ID del usuario a eliminar
     */
    public void deleteUser(int id) {
        usrepo.deleteById(id);
    }
    
    /**
     * Carga un usuario específico por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario a cargar
     * @return un objeto UserDetails que contiene la información del usuario
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuario: " + username);
        User user = getUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        System.out.println("Contraseña guardada: " + user.getUsPassw());
        return new org.springframework.security.core.userdetails.User(
                user.getUsName(),
                user.getUsPassw(),
                new ArrayList<>()
        );
    }
}
