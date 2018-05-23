package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.models.Topic;
import webdev.repositories.CourseRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/api/lesson/{lessonId}/topic")
    public Topic createTopic(@PathVariable("lessonId") int lessonId, @RequestBody Topic newTopic) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);

        if(data.isPresent()) {
            Lesson lesson = data.get();
            newTopic.setLesson(lesson);
            return topicRepository.save(newTopic);
        }
        return null;
    }

    @DeleteMapping("/api/topic/{topicId}")
    public void deleteTopic(@PathVariable("topicId") int topicId) {
        topicRepository.deleteById(topicId);
    }

    @GetMapping("/api/topic")
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }

    @GetMapping("/api/lesson/{lessonId}/topic")
    public List<Topic> findAllTopicsForModule( @PathVariable("lessonId") int lessonId) {
        Optional<Lesson> data = lessonRepository.findById(lessonId);
        if(data.isPresent()) {
            Lesson lesson = data.get();
            return lesson.getTopics();
        }
        return null;
    }
}