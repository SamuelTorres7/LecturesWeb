package com.samuel.lectureweb.service;

import com.samuel.lectureweb.domain.Lecture;
import com.samuel.lectureweb.repository.LectureRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar las operaciones relacionadas con las lecturas.
 * Esta clase proporciona métodos para obtener, guardar, eliminar y verificar lecturas en la base de datos.
 * 
 * @author Edrehy Torres
 */
@Service
public class LectureService {
    private final LectureRepository lrepo;

    /**
     * Constructor de la clase LectureService.
     *
     * @param lrepo El repositorio de lecturas que se utilizará para las operaciones de base de datos.
     */
    public LectureService(LectureRepository lrepo) {
        this.lrepo = lrepo;
    }
    
    /**
     * Obtiene una lista de todas las lecturas.
     *
     * @return Una lista de objetos Lecture.
     */
    public List<Lecture> getAllLect() {
        return lrepo.findAll();
    }
    
    /**
     * Obtiene una lectura por su ID.
     *
     * @param id El ID de la lectura a buscar.
     * @return Un objeto Optional que puede contener la lectura encontrada.
     */
    public Optional<Lecture> getLectById(int id) {
        return lrepo.findById(id);
    }
    
    /**
     * Guarda una nueva lectura en la base de datos.
     *
     * @param c El objeto Lecture a guardar.
     * @return El objeto Lecture que ha sido guardado.
     */
    public Lecture saveLect(Lecture c) {
        lrepo.save(c);
        return c;
    }
    
    /**
     * Elimina una lectura de la base de datos por su ID.
     *
     * @param id El ID de la lectura a eliminar.
     */
    public void deleteLecture(int id) {
        lrepo.deleteById(id);
    }
    
    /**
     * Obtiene una lista de lecturas asociadas a un usuario específico.
     *
     * @param usId El ID del usuario cuyas lecturas se desean obtener.
     * @return Una lista de objetos Lecture asociadas al usuario.
     */
    public List<Lecture> getLectureFrom(int usId) {
        return lrepo.findByUser_usId(usId);
    }
    
    /**
     * Verifica si un usuario ya ha leído un libro específico.
     *
     * @param usId El ID del usuario.
     * @param isbn El ISBN del libro a verificar.
     * @return true si el usuario ya ha leído el libro, false en caso contrario.
     */
    public boolean alreadyRead(int usId, String isbn) {
        return !lrepo.findLectureByUsBook(usId, isbn).isEmpty();
    }
}
