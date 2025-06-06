package com.samuel.lectureweb.controller;

import com.samuel.lectureweb.domain.Author;
import com.samuel.lectureweb.domain.Book;
import com.samuel.lectureweb.domain.Category;
import com.samuel.lectureweb.service.AuthorService;
import com.samuel.lectureweb.service.BookService;
import com.samuel.lectureweb.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para manejar las operaciones relacionadas con los libros.
 * Gestiona las solicitudes web para mostrar, añadir, editar y listar libros.
 *
 * @author Edrehy Torres
 */
@Controller
public class BookController {
    
    @Autowired
    private CategoryService cSer;
    @Autowired
    private BookService bSer;
    @Autowired
    private AuthorService aSer;
    
    /**
     * Muestra la página principal de la aplicación.
     * Si el usuario está autenticado, agrega su nombre y la lista de todos los libros al modelo.
     * También puede mostrar un mensaje de información si se proporciona.
     *
     * @param info Parámetro opcional para mostrar un mensaje de información.
     * @param model El objeto Model para pasar datos a la vista.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @return La vista "home".
     */
    @GetMapping("/home")
    public String showHomePage(@RequestParam(required = false) String info, Model model, Authentication auth){
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            model.addAttribute("nombre", username);
            model.addAttribute("libros",bSer.getAllBooks());
        }
        if(info != null){
            model.addAttribute("info",info);
        }
        return "/home";
    }
    
    /**
     * Muestra el formulario para añadir un nuevo libro.
     * Carga todas las categorías y autores disponibles para que el usuario pueda seleccionarlos.
     *
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "books-form".
     */
    @GetMapping("/books/add")
    public String addNewBook(Model model){
        List<Category> categorias = cSer.getAllCategory();
        List<Author> autores = aSer.getAllAuthor();
        model.addAttribute("categorias",categorias);
        model.addAttribute("authors",autores);
        return "/books-form";
    }
    
    /**
     * Muestra el formulario para editar un libro existente.
     * Recupera la información del libro por su ISBN y carga las categorías y autores para la edición.
     *
     * @param isbn El ISBN del libro a editar.
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "books-edit-form".
     */
    @GetMapping("/books/edit/{isbn}")
    public String editBook(@PathVariable("isbn") String isbn, Model model){
        Book b = bSer.getBookByIsbn(isbn).get();
        Author a = bSer.getAuthorsFrom(isbn).get(0);
        List<Category> categorias = cSer.getAllCategory();
        List<Author> authors = aSer.getAllAuthor();
        model.addAttribute("book",b);
        model.addAttribute("categorias",categorias);
        model.addAttribute("authors",authors);
        model.addAttribute("bAuthor",a);
        return "/books-edit-form";
    }
    
    /**
     * Procesa la solicitud para añadir un nuevo libro.
     * Valida si el libro ya existe por su ISBN y lo guarda en la base de datos si no es así.
     * Redirige al usuario a la página principal con un mensaje de éxito o error.
     *
     * @param isbn El ISBN del libro.
     * @param title El título del libro.
     * @param pages El número de páginas del libro.
     * @param category El ID de la categoría del libro.
     * @param description La descripción del libro.
     * @param imageUrl La URL de la imagen de la portada del libro.
     * @param author El ID del autor del libro.
     * @param rAtt El objeto RedirectAttributes para pasar atributos después de una redirección.
     * @return Una redirección a la página "/home" o "/books".
     */
    @PostMapping("/books/add")
    public String addBook(
        @RequestParam String isbn,
        @RequestParam String title,
        @RequestParam int pages,
        @RequestParam int category,
        @RequestParam String description,
        @RequestParam String imageUrl,
        @RequestParam int author,
        RedirectAttributes rAtt) {
        
        System.out.println("Agregando Libro: "+isbn+" - "+"title");
        
        if(!bSer.getBookByIsbn(isbn).isEmpty()){
            rAtt.addAttribute("info",
                    String.format("%s ya está en la biblioteca (ISBN: %s)",title,isbn));
            return "redirect:/home";
        }
        Book b = new Book();
        b.setIsbn(isbn);
        b.setTitle(title);
        b.setPages(pages);
        b.setCategory(cSer.getCatById(category).get());
        b.setDesc(description);
        b.setImgUrl(imageUrl);
        
        bSer.saveBook(b);
        
        bSer.addAuthorTo(author, isbn);
        
        return "redirect:/books";
    }
    
    /**
     * Procesa la solicitud para editar un libro existente.
     * Actualiza la información del libro en la base de datos.
     *
     * @param isbn El ISBN del libro a editar.
     * @param title El nuevo título del libro.
     * @param pages El nuevo número de páginas del libro.
     * @param category El nuevo ID de la categoría del libro.
     * @param description La nueva descripción del libro.
     * @param imageUrl La nueva URL de la imagen de la portada del libro.
     * @param author El nuevo ID del autor del libro.
     * @return Una redirección a la página "/books".
     */
    @PostMapping("/books/edit")
    public String editBook(
        @RequestParam String isbn,
        @RequestParam String title,
        @RequestParam int pages,
        @RequestParam int category,
        @RequestParam String description,
        @RequestParam String imageUrl,
        @RequestParam int author) {
        
        Book b = new Book();
        b.setIsbn(isbn);
        b.setTitle(title);
        b.setPages(pages);
        b.setCategory(cSer.getCatById(category).get());
        b.setDesc(description);
        b.setImgUrl(imageUrl);

        bSer.saveBook(b);
        
        return "redirect:/books"; // redirigir a la página principal después de agregar
    }
    
    /**
     * Muestra la página con la lista de libros, permitiendo filtrar por consulta de texto y/o categoría.
     *
     * @param query Parámetro opcional para buscar libros por título o descripción.
     * @param categoriaId Parámetro opcional para filtrar libros por el ID de la categoría.
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "books".
     */
    @GetMapping("/books")
    public String showBooks(
        @RequestParam(name = "query", required = false) String query,
        @RequestParam(name = "categoria", required = false) Integer categoriaId,
        Model model
    ){
        List<Category> categorias = cSer.getAllCategory();
        List<Book> libros;

        // Si hay filtro de búsqueda y/o categoría
        if ((query != null && !query.isBlank()) || categoriaId != null) {
            libros = bSer.findBooksByCriteria(query, categoriaId);
        } else {
            libros = bSer.getAllBooks(); // Sin filtros, mostrar todos
        }
        
        model.addAttribute("libros", libros);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selectedCategoria", categoriaId);

        return "/books";
    }
}