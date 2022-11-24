package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyDoesNotExistException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    public static final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        LOG.info("Was invoked method for add faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        LOG.error("There is not faculty with id = " + id);
        return facultyRepository.findById(id).orElseThrow(FacultyDoesNotExistException::new);
    }

    public Faculty editFaculty(Faculty faculty) {
        LOG.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        LOG.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        LOG.info("Was invoked method for find faculty by color");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrName(String colorOrName) {
        LOG.info("Was invoked method for find faculty by color or name");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName);
    }
}
