/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samuel.lectureweb.repository;

import com.samuel.lectureweb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author edreh
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
