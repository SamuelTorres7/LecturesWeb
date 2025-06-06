package com.samuel.lectureweb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa la entidad de una lectura en la base de datos.
 * Esta clase mapea la tabla "LECTURE" y define sus propiedades como ID, usuario, libro, páginas leídas, estado y fechas de inicio y fin.
 * 
 * @author edreh
 */
@Entity
@Table(name = "LECTURE")
public class Lecture {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECT_ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "US_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOOK_ISBN")
    private Book book;
    
    @Column(name = "LECT_RPAGES")
    private int readPages;

    @Column(name = "LECT_STATUS")
    private String status;

    @Column(name = "LECT_DATE_START", nullable = true)
    private String dateStart;

    @Column(name = "LECT_DATE_END", nullable = true)
    private String dateEnd;

    /**
     * Constructor por defecto de la clase Lecture.
     * Inicializa una nueva instancia de Lecture sin establecer valores.
     */
    public Lecture() {
    }

    /**
     * Constructor de la clase Lecture con todos los campos.
     *
     * @param id El ID único de la lectura.
     * @param user El usuario que realiza la lectura.
     * @param book El libro que se está leyendo.
     * @param readPages El número de páginas leídas.
     * @param status El estado de la lectura.
     * @param dateStart La fecha de inicio de la lectura.
     * @param dateEnd La fecha de fin de la lectura.
     */
    public Lecture(int id, User user, Book book, int readPages, String status, String dateStart, String dateEnd) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.readPages = readPages;
        this.status = status;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    /**
     * Obtiene el ID de la lectura.
     *
     * @return El ID de la lectura.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la lectura.
     *
     * @param id El nuevo ID de la lectura.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario que realiza la lectura.
     *
     * @return El usuario de la lectura.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario que realiza la lectura.
     *
     * @param user El nuevo usuario de la lectura.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene el libro que se está leyendo.
     *
     * @return El libro de la lectura.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Establece el libro que se está leyendo.
     *
     * @param book El nuevo libro de la lectura.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Obtiene el número de páginas leídas.
     *
     * @return El número de páginas leídas.
     */
    public int getReadPages() {
        return readPages;
    }

    /**
     * Establece el número de páginas leídas.
     *
     * @param readPages El nuevo número de páginas leídas.
     */
    public void setReadPages(int readPages) {
        this.readPages = readPages;
    }

    /**
     * Obtiene el estado de la lectura.
     *
     * @return El estado de la lectura.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado de la lectura.
     *
     * @param status El nuevo estado de la lectura.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene la fecha de inicio de la lectura.
     *
     * @return La fecha de inicio de la lectura.
     */
    public String getDateStart() {
        return dateStart;
    }
    
    /**
     * Establece la fecha de inicio de la lectura.
     *
     * @param dateStart La nueva fecha de inicio de la lectura.
     */
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Obtiene la fecha de fin de la lectura.
     *
     * @return La fecha de fin de la lectura.
     */
    public String getDateEnd() {
        return dateEnd;
    }

    /**
     * Establece la fecha de fin de la lectura.
     *
     * @param dateEnd La nueva fecha de fin de la lectura.
     */
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}

    