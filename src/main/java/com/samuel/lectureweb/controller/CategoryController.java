package com.samuel.lectureweb.controller;

import com.samuel.lectureweb.domain.Category;
import com.samuel.lectureweb.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author edreh
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService catServ;

    // --- Manejo de la lista de categorías ---
    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Category> categories = catServ.getAllCategory(); // Método para obtener todas las categorías
        model.addAttribute("categories", categories);
        return "category-list"; // Retorna la vista "category/list.html"
    }

    // --- Añadir nueva categoría ---

    /**
     * Muestra el formulario para añadir una nueva categoría.
     * GET /categories/new
     */
    @GetMapping("categories/new")
    public String showAddCategoryForm(Model model) {
        // Se añade un nuevo objeto Category al modelo para que el formulario lo llene
        model.addAttribute("category", new Category());
        return "category-form"; // Retorna la vista "category/add-category.html"
    }

    /**
     * Procesa la solicitud POST para guardar una nueva categoría.
     * POST /categories/save
     */
    @PostMapping("categories/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        catServ.saveCat(category);
        return "redirect:/categories";
    }

    /**
     * Muestra el formulario para editar una categoría existente.
     * GET /categories/edit/{id}
     */
    @GetMapping("categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") int id, Model model) {
        Optional<Category> category = catServ.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get()); // Añade la categoría al modelo
            return "category-edit-form"; // Retorna la vista "category/edit-category.html"
        } else {
            // Manejar el caso donde la categoría no se encuentra, por ejemplo, redirigir a una página de error o a la lista
            return "redirect:/categories"; // O podrías tener una vista de error
        }
    }

    /**
     * Procesa la solicitud POST para actualizar una categoría existente.
     * POST /categories/update
     */
    @PostMapping("categories/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        catServ.updateCategory(category); // Llama al servicio para actualizar la categoría
        return "redirect:/categories"; // Redirige a la lista de categorías después de actualizar
    }

    // --- Eliminar categoría (opcional, pero común) ---

    /**
     * Elimina una categoría por su ID.
     * GET /categories/delete/{id}
     */
    @GetMapping("categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        catServ.deleteCategory(id); // Llama al servicio para eliminar la categoría
        return "redirect:/categories"; // Redirige a la lista de categorías
    }
}
