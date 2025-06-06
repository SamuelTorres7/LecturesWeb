package com.samuel.lectureweb.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Representa la entidad de un usuario en la base de datos.
 * Esta clase mapea la tabla "USERL" y define sus propiedades como ID, nombre y contraseña.
 * 
 * @author edreh
 */
@Entity
@Table(name = "USERL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "US_ID")
    private int usId;

    @Column(name = "US_NAME")
    private String usName;

    @Column(name = "US_PASSW")
    private String usPassw;

    /**
     * Constructor por defecto de la clase User.
     * Inicializa una nueva instancia de User sin establecer valores.
     */
    public User() {
    }

    /**
     * Constructor de la clase User con todos los campos.
     *
     * @param usId El ID único del usuario.
     * @param usName El nombre del usuario.
     * @param usPassw La contraseña del usuario.
     */
    public User(int usId, String usName, String usPassw) {
        this.usId = usId;
        this.usName = usName;
        this.usPassw = usPassw;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getUsId() {
        return usId;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param usId El nuevo ID del usuario.
     */
    public void setUsId(int usId) {
        this.usId = usId;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getUsName() {
        return usName;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param usName El nuevo nombre del usuario.
     */
    public void setUsName(String usName) {
        this.usName = usName;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getUsPassw() {
        return usPassw;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param usPassw La nueva contraseña del usuario.
     */
    public void setUsPassw(String usPassw) {
        this.usPassw = usPassw;
    }
}
