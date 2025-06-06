package com.samuel.lectureweb.service;

import com.samuel.lectureweb.domain.Author;
import com.samuel.lectureweb.domain.Book;
import com.samuel.lectureweb.repository.AuthorRepository;
import com.samuel.lectureweb.repository.BookAuthorRepository;
import com.samuel.lectureweb.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar las operaciones relacionadas con los libros.
 * Esta clase proporciona métodos para obtener, guardar, eliminar libros y gestionar autores asociados.
 * 
 * @author Edrehy Torres
 */
@Service
public class BookService {
    private final BookRepository brepo;
    private final BookAuthorRepository baRepo;

    @Autowired
    private AuthorService aSer;

    /**
     * Constructor de la clase BookService.
     *
     * @param brepo El repositorio de libros que se utilizará para las operaciones de base de datos.
     * @param baRepo El repositorio de autores de libros que se utilizará para las operaciones de base de datos.
     */
    public BookService(BookRepository brepo, BookAuthorRepository baRepo) {
        this.brepo = brepo;
        this.baRepo = baRepo;
    }
    
    /**
     * Obtiene una lista de todos los libros.
     *
     * @return Una lista de objetos Book.
     */
    public List<Book> getAllBooks() {
        return brepo.findAll();
    }
    
    /**
     * Busca libros según criterios de título y categoría.
     *
     * @param title El título del libro a buscar.
     * @param categoryId El ID de la categoría del libro a buscar.
     * @return Una lista de libros que coinciden con los criterios.
     */
    public List<Book> findBooksByCriteria(String title, Integer categoryId) {
        // Si ambos son null o vacíos, devolvemos todos
        if ((title == null || title.isBlank()) && categoryId == null) {
            return brepo.findAll();
        }
        return brepo.findBooksByCriteria(title, categoryId);
    }
    
    /**
     * Obtiene un libro por su ISBN.
     *
     * @param id El ISBN del libro a buscar.
     * @return Un objeto Optional que puede contener el libro encontrado.
     */
    public Optional<Book> getBookByIsbn(String id) {
        return brepo.findById(id);
    }
    
    /**
     * Guarda un nuevo libro en la base de datos.
     *
     * @param b El objeto Book a guardar.
     * @return El objeto Book que ha sido guardado.
     */
    public Book saveBook(Book b) {
        brepo.save(b);
        return b;
    }
    
    /**
     * Elimina un libro de la base de datos por su ISBN.
     *
     * @param id El ISBN del libro a eliminar.
     */
    public void deleteBook(String id) {
        brepo.deleteById(id);
    }
    
    /**
     * Obtiene una lista de libros asociados a un autor específico.
     *
     * @param autId El ID del autor.
     * @return Una lista de objetos Book asociados al autor.
     */
    public List<Book> getBooksFrom(int autId) {
        List<String> isbns = baRepo.findBooksByAuthor(autId);
        List<Book> books = new ArrayList<>();
        for (String s : isbns) {
            getBookByIsbn(s).ifPresent(books::add);
        }
        return books;
    }
    
    /**
     * Obtiene una lista de autores asociados a un libro específico.
     *
     * @param isbn El ISBN del libro.
     * @return Una lista de objetos Author asociados al libro.
     */
    public List<Author> getAuthorsFrom(String isbn) {
        List<Integer> ids = baRepo.findAuthorsByBook(isbn);
        List<Author> authors = new ArrayList<>();
        for (int s : ids) {
            aSer.getAuthorById(s).ifPresent(authors::add);
        }
        return authors;
    }
    
    /**
     * Agrega un autor a un libro específico.
     *
     * @param autId El ID del autor a agregar.
     * @param isbn El ISBN del libro al que se agregará el autor.
     */
    public void addAuthorTo(int autId, String isbn) {
        baRepo.addAuthorToBook(autId, isbn);
    }
    
    /* Elimina un autor de un libro específico.
     *
     * @param autId El ID del autor a eliminar.
     * @param isbn El ISBN del libro del que se eliminará el autor.
     */
    public void removeAuthorFrom(int autId, String isbn) {
        baRepo.removeAuthorFromBook(autId, isbn);
    }
    
    /**
     * Actualiza la asociación de un autor en un libro específico.
     *
     * @param autIdOld El ID del autor antiguo que se reemplazará.
     * @param autIdNew El ID del nuevo autor que se asociará.
     * @param isbn El ISBN del libro en el que se actualizará la asociación.
     */
    public void updateAuthorFrom(int autIdOld, int autIdNew, String isbn) {
        baRepo.updateAuthorToBook(autIdOld, autIdNew, isbn);
    }
}