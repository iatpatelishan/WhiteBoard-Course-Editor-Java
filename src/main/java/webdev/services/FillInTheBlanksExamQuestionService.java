package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.exam.FillInTheBlanksExamQuestion;
import webdev.models.exam.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.exam.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.exam.MultipleChoiceExamQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlanksExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    FillInTheBlanksExamQuestionRepository fillInTheBlanksExamQuestionRepository;

    @GetMapping("/api/question/blanks/{id}")
    public Optional<FillInTheBlanksExamQuestion> findQuestionById(@PathVariable("id") int id) {
        return fillInTheBlanksExamQuestionRepository.findById(id);
    }

    @PostMapping("/api/exam/{eid}/blanks")
    public FillInTheBlanksExamQuestion createQuestion(@PathVariable("eid") int eid, @RequestBody FillInTheBlanksExamQuestion question) {
        Optional<Exam> data = examRepository.findById(eid);
        if (data.isPresent()) {
            question.setId(null);
            question.setExam(data.get());
            return fillInTheBlanksExamQuestionRepository.save(question);
        }
        return null;
    }

    @PutMapping("/api/question/blanks/{id}")
    public FillInTheBlanksExamQuestion updateQuestion(@PathVariable("id") int id, @RequestBody FillInTheBlanksExamQuestion newQuestion) {
        Optional<FillInTheBlanksExamQuestion> data = fillInTheBlanksExamQuestionRepository.findById(id);
        if (data.isPresent()) {
            FillInTheBlanksExamQuestion question = data.get();
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

            if(newQuestion.getVariables()!=null){
                question.setVariables(newQuestion.getVariables());
            }

            return fillInTheBlanksExamQuestionRepository.save(question);
        }
        return null;
    }
}
