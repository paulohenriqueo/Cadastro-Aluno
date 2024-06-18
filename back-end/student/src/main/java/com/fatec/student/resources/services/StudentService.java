package com.fatec.student.resources.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.student.dto.StudentRequest;
import com.fatec.student.dto.StudentResponse;
import com.fatec.student.entities.Student;
import com.fatec.student.mappers.StudentMapper;
import com.fatec.student.repositories.StudentRepositoriry;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepositoriry studentRepositoriry;

        public List<StudentResponse> getStudents(){

            List <Student> students = studentRepositoriry.findAll();

            return students.stream()
                                    .map(s -> StudentMapper.toDTO(s))                
                                    .collect(Collectors.toList());                        
            
        }
        
    public StudentResponse getStudentById(int id){

        Student stundent = studentRepositoriry.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Aluno não cadastrado")
        ); 

        return StudentMapper.toDTO(stundent);

    }

    public void deleteStudentById(int id){
        if (this.studentRepositoriry.existsById(id)) {
            this.studentRepositoriry.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Aluno não cadstrado");
        }
    }

    public StudentResponse save(StudentRequest request){
        Student student = StudentMapper.toEntity(request);

        return StudentMapper.toDTO(this.studentRepositoriry.save(student));  
    }

public void update(int id, StudentRequest request) {
        try {
            Student aux = studentRepositoriry.getReferenceById(id);
            aux.setCourse(request.course());
            aux.setName(request.name());
            this.studentRepositoriry.save(aux);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Aluno inexistente no BD");
        }
    }

}
