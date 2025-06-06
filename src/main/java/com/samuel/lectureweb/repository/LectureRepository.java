/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samuel.lectureweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.samuel.lectureweb.domain.Lecture;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author edreh
 */
public interface LectureRepository extends JpaRepository<Lecture,Integer> {
    public List<Lecture> findByUser_usId(int usId);
    @Query(
        value = "SELECT * FROM LECTURE WHERE US_ID = ?1 AND LECT_STATUS = ?2;",
        nativeQuery  = true
    )
    public List<Lecture> findLectureByUsIdStatus(int usId, String lectStatus);
    
    @Query(
        value = "SELECT * FROM LECTURE WHERE US_ID = ?1 AND BOOK_ISBN = ?2;",
        nativeQuery = true
    )
    public Optional<Lecture> findLectureByUsBook(int usId, String ibsn);
}
