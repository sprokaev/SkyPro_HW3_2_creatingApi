package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentDoesNotExistException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    public static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        LOG.info("Was invoked method for add student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        LOG.error("There is not student with id = " + id);
        return studentRepository.findById(id).orElseThrow(StudentDoesNotExistException::new);
    }

    public Student editStudent(Student student) {
        LOG.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        LOG.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        LOG.info("Was invoked method for find student by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        LOG.info("Was invoked method for find student by age between");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public int totalCountOfStudents() {
        LOG.info("Was invoked method for total count of students");
        return studentRepository.totalCountOfStudents();
    }

    public double averageAgeOfStudents() {
        LOG.info("Was invoked method for average age of students");
        return studentRepository.averageAgeOfStudents();
    }

    public List<Student> lastStudents(int count) {
        LOG.info("Was invoked method for displays last students");
        return studentRepository.lastStudents(count);
    }
}
