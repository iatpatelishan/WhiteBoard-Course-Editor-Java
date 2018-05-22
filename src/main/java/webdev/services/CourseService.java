package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Course;
import webdev.repositories.CourseRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/course")
    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/api/course/{id}")
    public Optional<Course> findCourseById(@PathVariable("id") int id) {
        return courseRepository.findById(id);
    }

    @PostMapping("/api/course")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/api/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") int id) {
        courseRepository.deleteById(id);
    }

    @PutMapping("/api/course/{courseId}")
    public Course updateCourse(@PathVariable("courseId") int id, @RequestBody Course newCourse){
        Course oldCourse = findCourseById(id).orElse(null);
        if(oldCourse!=null){
            if(newCourse.getTitle()!=null){
                oldCourse.setTitle(newCourse.getTitle());
            }
            return courseRepository.save(oldCourse);
        }
        return null;
    }
}