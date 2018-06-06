package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExamService {
    @Autowired
    ExamRepository examRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/api/exam")
    public Iterable<Exam> findAllExam() {
        return examRepository.findAll();
    }

    @GetMapping("/api/exam/{id}")
    public Optional<Exam> findExamById(@PathVariable("id") int id) {
        return examRepository.findById(id);
    }

    @GetMapping("/api/topic/{topicId}/exam")
    public List<Exam> findAllExamsForTopic(@PathVariable("topicId") int topicId) {
        Optional<Topic> data = topicRepository.findById(topicId);

        if (data.isPresent()) {
            return examRepository.findAllExamsForTopic(data.get());
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/exam")
    public Exam createExam(@PathVariable("topicId") int topicId, @RequestBody Exam exam) {
        Optional<Topic> data = topicRepository.findById(topicId);

        if (data.isPresent()) {
            exam.setId(null);
            exam.setTopic(data.get());
            return examRepository.save(exam);
        }

        return null;
    }

    @DeleteMapping("/api/exam/{id}")
    public void deleteExam(@PathVariable("id") int id) {
        examRepository.deleteById(id);
    }

    @PutMapping("/api/exam/{id}")
    public Exam updateExam(@PathVariable("id") int id, @RequestBody Exam newExam) {
        Optional<Exam> data = examRepository.findById(id);
        if (data.isPresent()) {
            Exam exam = data.get();
            if(newExam.getQuestions()!=null){
                exam.setQuestions(newExam.getQuestions());
            }
            return examRepository.save(exam);
        }
        return null;
    }


}
