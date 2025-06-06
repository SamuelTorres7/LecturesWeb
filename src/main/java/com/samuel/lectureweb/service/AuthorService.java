package com.samuel.lectureweb.service;

import com.samuel.lectureweb.domain.Author;
import com.samuel.lectureweb.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar las operaciones relacionadas con los autores.
 * Esta clase proporciona métodos para obtener, guardar y eliminar autores en la base de datos.
 * 
 * @author Edrehy
 */
@Service
public class AuthorService {
    private final AuthorRepository arepo;

    /**
     * Constructor de la clase AuthorService.
     *
     * @param arepo El repositorio de autores que se utilizará para las operaciones de base de datos.
     */
    public AuthorService(AuthorRepository arepo) {
        this.arepo = arepo;
    }
    
    /**
     * Obtiene una lista de todos los autores.
     *
     * @return Una lista de objetos Author.
     */
    public List<Author> getAllAuthor() {
        return arepo.findAll();
    }
    
    /**
     * Obtiene un autor por su ID.
     *
     * @param id El ID del autor a buscar.
     * @return Un objeto Optional que puede contener el autor encontrado.
     */
    public Optional<Author> getAuthorById(int id) {
        return arepo.findById(id);
    }
    
    /**
     * Obtiene un autor por su nombre.
     *
     * @param name El nombre del autor a buscar.
     * @return El objeto Author correspondiente al nombre proporcionado.
     */
    public Author getAuthorByName(String name) {
        return arepo.findByName(name);
    }
    
    /**
     * Guarda un nuevo autor en la base de datos.
     *
     * @param c El objeto Author a guardar.
     * @return El objeto Author que ha sido guardado.
     */
    public Author saveAuthor(Author c) {
        arepo.save(c);
        return c;
    }
    
    /**
     * Elimina un autor de la base de datos por su ID.
     *
     * @param id El ID del autor a eliminar.
     */
    public void deleteAuthor(int id) {
        arepo.deleteById(id);
    }
}
