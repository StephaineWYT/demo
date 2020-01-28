package wen.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wen.demo.entity.Student;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    public List<Student> findByName(String name);

    public List<Student> deleteByName(String name);

}
