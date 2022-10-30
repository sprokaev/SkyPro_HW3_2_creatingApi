package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Long> {

    ArrayList<Student> findByAge(int age);
}
