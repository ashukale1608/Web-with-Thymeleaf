package webwiththymeleaf.example.webwiththymeleaf.dov;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webwiththymeleaf.example.webwiththymeleaf.model.Student;
public interface StudentRepo extends JpaRepository<Student,Integer>{

    Optional<Student> findByName(String name);
    
}
