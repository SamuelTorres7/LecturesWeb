/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samuel.lectureweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuel.lectureweb.domain.Author;
import java.util.List;
/**
 *
 * @author edreh
 */
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    public Author findByName(String name);
}
