package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Course;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/api/assignment")
    public Iterable<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }

    @GetMapping("/api/assignment/{id}")
    public Optional<Assignment> findAssignmentById(@PathVariable("id") int id) {
        return assignmentRepository.findById(id);
    }

    @GetMapping("/api/topic/{topicId}/assignment")
    public List<Assignment> findAllAssignmentsForTopic(@PathVariable("topicId") int topicId) {
        Optional<Topic> data = topicRepository.findById(topicId);

        if (data.isPresent()) {
            return assignmentRepository.findAllAssignmentsForTopic(data.get());
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/assignment")
    public Assignment createAssignment(@PathVariable("topicId") int topicId, @RequestBody Assignment assignment) {
        Optional<Topic> data = topicRepository.findById(topicId);

        if (data.isPresent()) {
            assignment.setId(null);
            assignment.setTopic(data.get());
            return assignmentRepository.save(assignment);
        }

        return null;
    }

    @DeleteMapping("/api/assignment/{id}")
    public void deleteWidget(@PathVariable("id") int id) {
        assignmentRepository.deleteById(id);
    }


}
