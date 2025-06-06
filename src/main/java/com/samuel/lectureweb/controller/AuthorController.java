package com.samuel.lectureweb.controller;

import com.samuel.lectureweb.domain.Author;
import com.samuel.lectureweb.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    // --- Manejo de la lista de autores ---

    /**
     * Muestra el listado de todos los autores.
     * GET /authors
     */
    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthor(); // Obtiene todos los autores del servicio
        model.addAttribute("authors", authors); // Añade la lista de autores al modelo para la vista
        return "authors"; // Retorna la vista "author/list-author.html"
    }

    // --- Añadir nuevo autor ---

    /**
     * Muestra el formulario para añadir un nuevo autor.
     * GET /authors/new
     */
    @GetMapping("author/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author()); // Añade un nuevo objeto Author al modelo
        return "author-form"; // Retorna la vista "author/add-author.html"
    }

    /**
     * Procesa la solicitud POST para guardar un nuevo autor.
     * POST /authors/save
     */
    @PostMapping("/author/save")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.saveAuthor(author); // Guarda el autor usando el servicio
        return "redirect:/authors"; // Redirige a la lista de autores después de guardar
    }

    // --- Editar autor existente ---

    /**
     * Muestra el formulario para editar un autor existente.
     * GET /authors/edit/{id}
     */
    @GetMapping("/author/edit/{id}")
    public String showEditAuthorForm(@PathVariable int id, Model model) { // Captura el ID de la URL
        Optional<Author> author = authorService.getAuthorById(id); // Busca el autor por ID
        if (author.isPresent()) {
            model.addAttribute("author", author.get()); // Añade el autor al modelo si existe
            return "author-edit-form"; // Retorna la vista "author/edit-author.html"
        } else {
            // Manejar el caso donde el autor no se encuentra
            return "redirect:/authors"; // Redirige a la lista o a una página de error
        }
    }

    /**
     * Procesa la solicitud POST para actualizar un autor existente.
     * POST /authors/update
     */
    @PostMapping("/author/update")
    public String updateAuthor(@ModelAttribute("author") Author author) {
        // El objeto 'author' ya contendrá el ID y los campos actualizados del formulario
        authorService.saveAuthor(author); // save() de JpaRepository funciona para insertar y actualizar
        return "redirect:/authors"; // Redirige a la lista de autores después de actualizar
    }

    // --- Eliminar autor ---

    /**
     * Elimina un autor por su ID.
     * GET /authors/delete/{id}
     */
    @GetMapping("/auhtor/delete/{id}")
    public String deleteAuthor(@PathVariable int id) { // Captura el ID de la URL
        authorService.deleteAuthor(id); // Elimina el autor usando el servicio
        return "redirect:/authors"; // Redirige a la lista de autores
    }
}