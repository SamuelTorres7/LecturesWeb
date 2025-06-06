package com.samuel.lectureweb.controller;

import com.samuel.lectureweb.domain.Author;
import com.samuel.lectureweb.domain.Book;
import com.samuel.lectureweb.domain.Lecture;
import com.samuel.lectureweb.domain.User;
import com.samuel.lectureweb.service.BookService;
import com.samuel.lectureweb.service.LectureService;
import com.samuel.lectureweb.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para manejar las operaciones relacionadas con las lecturas de los usuarios.
 * Permite a los usuarios ver, añadir, editar y eliminar sus lecturas de libros.
 *
 * @author Edrehy Torres
 */
@Controller
public class LectureController {
    @Autowired
    private LectureService lServ;
    @Autowired
    private UserService usServ;
    @Autowired
    private BookService bServ;
    
    /**
     * Muestra la lista de lecturas de un usuario autenticado.
     * Recupera el usuario actual y sus lecturas asociadas para mostrarlas en la vista.
     *
     * @param model El objeto Model para pasar datos a la vista.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @return La vista "/lectures" que muestra las lecturas del usuario.
     */
    @GetMapping("/lectures")
    public String showLectures(Model model,Authentication auth){
        String name = auth.getName();
            Optional<User> us = usServ.getUserByName(name);
        List<Lecture> lectures = lServ.getLectureFrom(us.get().getUsId());
        model.addAttribute("lectures", lectures);
        model.addAttribute("username",us.get().getUsName());
        return "/lectures";
    }

    /**
     * Muestra el formulario para añadir una nueva lectura para un libro específico.
     * Verifica si el libro ya ha sido añadido a las lecturas del usuario.
     *
     * @param bookId El ISBN del libro al que se le añadirá una lectura.
     * @param model El objeto Model para pasar datos a la vista.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @param reAtt El objeto RedirectAttributes para pasar atributos después de una redirección.
     * @return La vista "/lecture-form" para añadir la lectura, o una redirección a "/home" si el libro ya está en lecturas.
     * @throws RuntimeException Si el libro no es encontrado.
     */
    @GetMapping("/lectures/add")
    public String showAddForm(@RequestParam("bookId") String bookId,
                                  Model model, Authentication auth,
                                  RedirectAttributes reAtt) {
        Book book = bServ.getBookByIsbn(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        String name = auth.getName();
        Optional<User> us = usServ.getUserByName(name);
        if(lServ.alreadyRead(us.get().getUsId(),bookId)){
            reAtt.addFlashAttribute("info", "Este libro ya está en lecturas");
            return "redirect:/home";
        }
        Lecture lecture = new Lecture();
        lecture.setBook(book);
        lecture.setStatus("Pendiente");
        
        model.addAttribute("lecture", lecture);

        return "/lecture-form";
    }

    /**
     * Muestra el formulario para editar una lectura existente.
     *
     * @param id El ID de la lectura a editar.
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "/lecture-edit-form" para editar la lectura.
     * @throws RuntimeException Si la lectura no es encontrada.
     */
    @GetMapping("lectures/edit/{id}")
    public String showEditForm(@PathVariable("id") int id,
                               Model model) {
        Lecture lecture = lServ.getLectById(id)
                .orElseThrow(() -> new RuntimeException("Lectura no encontrada"));
        model.addAttribute("lecture", lecture);
        return "/lecture-edit-form";
    }

    /**
     * Muestra una página de confirmación antes de eliminar una lectura.
     *
     * @param id El ID de la lectura a confirmar la eliminación.
     * @param model El objeto Model para pasar datos a la vista.
     * @return La vista "delete-lect" que solicita la confirmación de eliminación.
     */
    @GetMapping("/lectures/confirm/{id}")
    public String confirmDelete(@PathVariable("id") int id, Model model){
        model.addAttribute("lecture",lServ.getLectById(id).get());
        return "delete-lect";
    }

    /**
     * Elimina una lectura por su ID y redirige a la lista de lecturas del usuario.
     *
     * @param id El ID de la lectura a eliminar.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @param model El objeto Model para pasar datos a la vista.
     * @return Una redirección a la página "/lectures" para mostrar la lista actualizada.
     */
    @GetMapping("/lectures/delete/{id}")
    public String deleteLecture(@PathVariable("id") int id, Authentication auth, Model model){
        lServ.deleteLecture(id);
        Optional<User> u = usServ.getUserByName(auth.getName());
        List<Lecture> lectures = lServ.getLectureFrom(u.get().getUsId());
        model.addAttribute("lectures", lectures);
        return "redirect:/lectures";
    }
    
    /**
     * Guarda una nueva lectura o actualiza una existente.
     * Asocia la lectura con el usuario autenticado.
     *
     * @param lecture El objeto Lecture recibido del formulario, que será guardado o actualizado.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @param model El objeto Model para pasar datos a la vista.
     * @return Una redirección a la página "/lectures" para mostrar la lista actualizada.
     */
    @PostMapping("/lectures/save")
    public String saveLecture(@ModelAttribute("lecture") Lecture lecture,
                                  Authentication auth, Model model) {
        String name = auth.getName();
        Optional<User> us = usServ.getUserByName(name);
        if(lecture.getDateEnd() != null && lecture.getDateEnd().trim().isEmpty()) {
            lecture.setDateEnd(null);
        }
        lecture.setUser(us.get());
        lServ.saveLect(lecture);
        return "redirect:/lectures";
    }
    
    /**
     * Muestra los detalles de una lectura específica, incluyendo los autores del libro asociado.
     *
     * @param id El ID de la lectura cuyos detalles se desean mostrar.
     * @param model El objeto Model para pasar datos a la vista.
     * @param auth El objeto Authentication que contiene la información del usuario autenticado.
     * @return La vista "lecture-details" que muestra los detalles de la lectura.
     */
    @GetMapping("/lecture/details/{id}")
    public String showLectDetails(@PathVariable("id") int id, Model model, Authentication auth){
        Lecture lect = lServ.getLectById(id).get();
        List<Author> authors = bServ.getAuthorsFrom(lect.getBook().getIsbn());
        model.addAttribute("authors", authors);
        model.addAttribute("lecture",lect);
        model.addAttribute("username",auth.getName());
        return "lecture-details";
    }
}