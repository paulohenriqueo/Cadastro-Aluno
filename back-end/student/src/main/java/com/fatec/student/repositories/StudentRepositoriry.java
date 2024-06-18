package com.fatec.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.student.entities.Student;

public interface StudentRepositoriry extends JpaRepository<Student,Integer> {
    
}
