/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samuel.lectureweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuel.lectureweb.domain.User;
/**
 *
 * @author edreh
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    
}
