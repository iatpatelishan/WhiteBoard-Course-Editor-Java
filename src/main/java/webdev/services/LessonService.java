package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Course;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @PostMapping("/api/module/{moduleId}/lesson")
    public Lesson createLesson(@PathVariable("moduleId") int moduleId, @RequestBody Lesson newLesson) {
        Optional<Module> data = moduleRepository.findById(moduleId);

        if(data.isPresent()) {
            Module module = data.get();
            newLesson.setModule(module);
            return lessonRepository.save(newLesson);
        }
        return null;
    }

    @DeleteMapping("/api/lesson/{lessonId}")
    public void deleteLesson(@PathVariable("lessonId") int lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @GetMapping("/api/lesson")
    public List<Lesson> findAllLessons() {
        return (List<Lesson>) lessonRepository.findAll();
    }

    @GetMapping("/api/module/{moduleId}/lesson")
    public List<Lesson> findAllLessonsForModule( @PathVariable("moduleId") int moduleId) {
        Optional<Module> data = moduleRepository.findById(moduleId);
        if(data.isPresent()) {
            Module module = data.get();
            return module.getLessons();
        }
        return null;
    }
}