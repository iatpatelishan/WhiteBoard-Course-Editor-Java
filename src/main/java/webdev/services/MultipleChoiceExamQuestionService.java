package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.exam.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.exam.MultipleChoiceExamQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    MultipleChoiceExamQuestionRepository multipleChoiceExamQuestionRepository;

    @GetMapping("/api/question/choice/{id}")
    public Optional<MultipleChoiceExamQuestion> findQuestionById(@PathVariable("id") int id) {
        return multipleChoiceExamQuestionRepository.findById(id);
    }

    @PostMapping("/api/exam/{eid}/choice")
    public MultipleChoiceExamQuestion createQuestion(@PathVariable("eid") int eid, @RequestBody MultipleChoiceExamQuestion question) {
        Optional<Exam> data = examRepository.findById(eid);
        if (data.isPresent()) {
            question.setId(null);
            question.setExam(data.get());
            question.setPoints(0);
            return multipleChoiceExamQuestionRepository.save(question);
        }
        return null;
    }

    @PutMapping("/api/question/choice/{id}")
    public MultipleChoiceExamQuestion updateQuestion(@PathVariable("id") int id, @RequestBody MultipleChoiceExamQuestion newQuestion) {
        Optional<MultipleChoiceExamQuestion> data = multipleChoiceExamQuestionRepository.findById(id);
        if (data.isPresent()) {
            MultipleChoiceExamQuestion question = data.get();
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

            if(newQuestion.getOptions()!=null){
                question.setOptions(newQuestion.getOptions());
            }
            if(newQuestion.getAnswer()!=null){
                question.setAnswer(newQuestion.getAnswer());
            }
            return multipleChoiceExamQuestionRepository.save(question);
        }
        return null;
    }
}
