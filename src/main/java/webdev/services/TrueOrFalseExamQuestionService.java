package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.exam.MultipleChoiceExamQuestion;
import webdev.models.exam.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.exam.MultipleChoiceExamQuestionRepository;
import webdev.repositories.exam.TrueOrFalseExamQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;

    @GetMapping("/api/question/truefalse/{id}")
    public Optional<TrueOrFalseExamQuestion> findQuestionById(@PathVariable("id") int id) {
        return trueOrFalseExamQuestionRepository.findById(id);
    }

    @PostMapping("/api/exam/{eid}/truefalse")
    public TrueOrFalseExamQuestion createQuestion(@PathVariable("eid") int eid, @RequestBody TrueOrFalseExamQuestion question) {
        Optional<Exam> data = examRepository.findById(eid);
        if (data.isPresent()) {
            question.setId(null);
            question.setExam(data.get());
            question.setPoints(0);
            return trueOrFalseExamQuestionRepository.save(question);
        }
        return null;
    }

    @PutMapping("/api/question/truefalse/{id}")
    public TrueOrFalseExamQuestion updateQuestion(@PathVariable("id") int id, @RequestBody TrueOrFalseExamQuestion newQuestion) {
        Optional<TrueOrFalseExamQuestion> data = trueOrFalseExamQuestionRepository.findById(id);
        if (data.isPresent()) {
            TrueOrFalseExamQuestion question = data.get();
            if(newQuestion.getTitle()!=null){
                question.setTitle(newQuestion.getTitle());
            }
            if(newQuestion.getDescription()!=null){
                question.setDescription(newQuestion.getDescription());
            }
            if(newQuestion.getPoints()!=null){
                question.setPoints(newQuestion.getPoints());
            }
            if(newQuestion.getInstructions()!=null){
                question.setInstructions(newQuestion.getInstructions());
            }
            if(newQuestion.getQuestionType()!=null){
                question.setQuestionType(newQuestion.getQuestionType());
            }
            if(newQuestion.getExam()!=null){
                question.setExam(newQuestion.getExam());
            }

            if(newQuestion.getIsTrue()!=null){
                question.setIsTrue(newQuestion.getIsTrue());
            }
            return trueOrFalseExamQuestionRepository.save(question);
        }
        return null;
    }
}
