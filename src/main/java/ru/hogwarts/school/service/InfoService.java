package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class InfoService {

    public static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    private final StudentRepository studentRepository;

    public InfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students.subList(0, 2));
        new Thread(() -> printStudents(students.subList(2, 4))).start();
        new Thread(() -> printStudents(students.subList(4, 6))).start();
    }

    private void printStudents(List<Student> students) {
        for (Student student : students) {
            LOG.info(student.getName());
        }
    }

    private synchronized void printStudentsSync(List<Student> students) {
        for (Student student : students) {
            LOG.info(student.getName());
        }
    }

    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudentsSync(students.subList(0, 2));
        new Thread(() -> printStudentsSync(students.subList(2, 4))).start();
        new Thread(() -> printStudentsSync(students.subList(4, 6))).start();
    }
}
