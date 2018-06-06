package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.models.exam.BaseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.exam.BaseExamQuestionRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BaseExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    BaseExamQuestionRepository baseExamQuestionRepository;

    @GetMapping("/api/exam/{id}/question")
    public List<BaseExamQuestion> findAllQuestionsForExam(@PathVariable("id") int id){
        Optional<Exam> data = examRepository.findById(id);
        if(data.isPresent()){
            return baseExamQuestionRepository.findAllQuestionsForExam(data.get());
        }
        return null;
    }
}
