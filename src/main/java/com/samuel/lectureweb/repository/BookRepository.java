/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samuel.lectureweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuel.lectureweb.domain.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author edreh
 */
public interface BookRepository extends JpaRepository<Book,String>{
    @Query(value = "SELECT * FROM BOOK WHERE " +
                   "(LOWER(BOOK_TITLE) LIKE LOWER(CONCAT('%', :title, '%')) OR :title IS NULL OR :title = '') AND " +
                   "(:categoryId IS NULL OR CAT_ID = :categoryId)",
           nativeQuery = true
    )
        List<Book> findBooksByCriteria(@Param("title") String title,
                                         @Param("categoryId") Integer categoryId);
}
