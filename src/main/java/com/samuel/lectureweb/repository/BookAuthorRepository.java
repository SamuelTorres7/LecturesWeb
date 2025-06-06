/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuel.lectureweb.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edreh
 */
@Repository
public class BookAuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookAuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Añadir relación Autor-Libro
    @Transactional
    public void addAuthorToBook(int authorId, String bookIsbn) {
        String sql = "INSERT INTO BOOK_AUTHOR (AUT_ID, BOOK_ISBN) VALUES (?, ?)";
        jdbcTemplate.update(sql, authorId, bookIsbn);
    }
    
    // Añadir relación Autor-Libro
    @Transactional
    public void updateAuthorToBook(int authorIdOld, int authorIdNew, String bookIsbn) {
        String sql = "UPDATE BOOK_AUTHOR SET AUT_ID = ?, BOOK_ISBN = ? WHERE AUT_ID = ? AND BOOK_ISBN = ?;";
        jdbcTemplate.update(sql, authorIdNew, bookIsbn, authorIdOld, bookIsbn);
    }
    
    // Eliminar relación Autor-Libro
    @Transactional
    public void removeAuthorFromBook(int authorId, String bookIsbn) {
        String sql = "DELETE FROM BOOK_AUTHOR WHERE AUT_ID = ? AND BOOK_ISBN = ?";
        jdbcTemplate.update(sql, authorId, bookIsbn);
    }

    // Obtener todos los autores de un libro
    public List<Integer> findAuthorsByBook(String bookIsbn) {
        String sql = "SELECT AUT_ID FROM BOOK_AUTHOR WHERE BOOK_ISBN = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, bookIsbn);
    }

    // Obtener todos los libros de un autor
    public List<String> findBooksByAuthor(int authorId) {
        String sql = "SELECT BOOK_ISBN FROM BOOK_AUTHOR WHERE AUT_ID = ?";
        return jdbcTemplate.queryForList(sql, String.class, authorId);
    }

    // Verificar si existe la relación
    public boolean existsRelation(int authorId, String bookIsbn) {
        String sql = "SELECT COUNT(*) FROM BOOK_AUTHOR WHERE AUT_ID = ? AND BOOK_ISBN = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, authorId, bookIsbn);
        return count != null && count > 0;
    }
}
