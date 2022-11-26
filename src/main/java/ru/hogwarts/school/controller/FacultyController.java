package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(params = "color")
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(params = "colorOrName")
    public ResponseEntity<Collection<Faculty>> findByColorOrName(@RequestParam String colorOrName) {
        if (colorOrName != null && !colorOrName.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorOrName(colorOrName));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFaculty(@PathVariable Long id) {
        Collection<Student> students = facultyService.findFaculty(id).getStudents();
        if (students == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/findTheLongestFacultyName")
    public String findTheLongestFacultyName() {
        return facultyService.findTheLongestFacultyName();
    }
}
