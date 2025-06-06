package com.samuel.lectureweb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa la entidad de un libro en la base de datos.
 * Esta clase mapea la tabla "BOOK" y define sus propiedades como ISBN, título, número de páginas, categoría, descripción e imagen.
 * 
 * @author edreh
 */
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "BOOK_ISBN")
    private String isbn;

    @Column(name = "BOOK_TITLE")
    private String title;

    @Column(name = "BOOK_PAGES")
    private int pages;

    @ManyToOne
    @JoinColumn(name = "CAT_ID")
    private Category category;

    @Column(name = "BOOK_DESC")
    private String desc;

    @Column(name = "BOOK_IMG_URL")
    private String imgUrl;

    /**
     * Constructor de la clase Book con todos los campos.
     *
     * @param isbn El ISBN del libro.
     * @param title El título del libro.
     * @param pages El número de páginas del libro.
     * @param category La categoría a la que pertenece el libro.
     * @param desc La descripción del libro.
     * @param imgUrl La URL de la imagen del libro.
     */
    public Book(String isbn, String title, int pages, Category category, String desc, String imgUrl) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.category = category;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    /**
     * Constructor por defecto de la clase Book.
     * Inicializa una nueva instancia de Book sin establecer valores.
     */
    public Book() {
    }

    /**
     * Obtiene el ISBN del libro.
     *
     * @return El ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     *
     * @param isbn El nuevo ISBN del libro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del libro.
     *
     * @param title El nuevo título del libro.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el número de páginas del libro.
     *
     * @return El número de páginas del libro.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Establece el número de páginas del libro.
     *
     * @param pages El nuevo número de páginas del libro.
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Obtiene la categoría del libro.
     *
     * @return La categoría del libro.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Establece la categoría del libro.
     *
     * @param category La nueva categoría del libro.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Obtiene la descripción del libro.
     *
     * @return La descripción del libro.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Establece la descripción del libro.
     *
     * @param desc La nueva descripción del libro.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Obtiene la URL de la imagen del libro.
     *
     * @return La URL de la imagen del libro.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Establece la URL de la imagen del libro.
     *
     * @param imgUrl La nueva URL de la imagen del libro.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
