package com.samuel.lectureweb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Representa la entidad de un autor en la base de datos.
 * Esta clase mapea la tabla "AUTHOR" y define sus propiedades.
 * @author Edrehy Torres
 */
@Entity
@Table(name = "AUTHOR")
public class Author {
    /**
     * El ID del Autor
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUT_ID")
    private int id;
    /**
     * El nombre del Autor
     */
    @Column(name = "AUT_NAME")
    private String name;
    /**
     * El apellido del Autor
     */
    @Column(name = "AUT_LNAME")
    private String lastname;
    
    /**
     * Constructor vacio para Author.
     */
    public Author() {
    }
    
    /**
     * Constructor de Author con todos sus atributos.
     * @param id
     * @param name
     * @param lastname 
     */
    public Author(int id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }
    
    /**
     * Obtiene el ID del Autor
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Establece un ID para el Autor
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Obtiene el nombre del Autor
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * Establece un nombre para el Autor
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Obtiene el apellido del Autor
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * Establece un apellido para el Autor
     * @param lastname 
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}