package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT count(id) FROM student", nativeQuery = true)
    int totalCountOfStudents();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    int averageAgeOfStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT : count", nativeQuery = true)
    List<Student> lastStudents(@Param("count") int count);
}
