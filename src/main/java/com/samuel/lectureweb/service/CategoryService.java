package com.samuel.lectureweb.service;

import com.samuel.lectureweb.domain.Category;
import com.samuel.lectureweb.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar las operaciones relacionadas con las categorías.
 * Esta clase proporciona métodos para obtener, guardar, eliminar y actualizar categorías en la base de datos.
 * 
 * @author Edrehy Torres
 */
@Service
public class CategoryService {
    private final CategoryRepository crepo;

    /**
     * Constructor de la clase CategoryService.
     *
     * @param crepo El repositorio de categorías que se utilizará para las operaciones de base de datos.
     */
    public CategoryService(CategoryRepository crepo) {
        this.crepo = crepo;
    }
    
    /**
     * Obtiene una lista de todas las categorías.
     *
     * @return Una lista de objetos Category.
     */
    public List<Category> getAllCategory() {
        return crepo.findAll();
    }
    
    /**
     * Obtiene una categoría por su ID.
     *
     * @param id El ID de la categoría a buscar.
     * @return Un objeto Optional que puede contener la categoría encontrada.
     */
    public Optional<Category> getCatById(int id) {
        return crepo.findById(id);
    }
    
    /**
     * Guarda una nueva categoría en la base de datos.
     *
     * @param c El objeto Category a guardar.
     * @return El objeto Category que ha sido guardado.
     */
    public Category saveCat(Category c) {
        crepo.save(c);
        return c;
    }
    
    /**
     * Elimina una categoría de la base de datos por su ID.
     *
     * @param id El ID de la categoría a eliminar.
     */
    public void deleteBook(int id) {
        crepo.deleteById(id);
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id El ID de la categoría a buscar.
     * @return Un objeto Optional que puede contener la categoría encontrada.
     */
    public Optional<Category> getCategoryById(int id) {
        return crepo.findById(id);
    }

    /**
     * Actualiza una categoría existente en la base de datos.
     *
     * @param category El objeto Category que contiene los datos actualizados.
     */
    public void updateCategory(Category category) {
        crepo.save(category);
    }

    /**
     * Elimina una categoría de la base de datos por su ID.
     *
     * @param id El ID de la categoría a eliminar.
     */
    public void deleteCategory(int id) {
        crepo.delete(getCatById(id).get());
    }
}
