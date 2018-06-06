package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Exam;
import webdev.models.exam.EssayExamQuestion;
import webdev.models.exam.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.exam.EssayExamQuestionRepository;
import webdev.repositories.exam.MultipleChoiceExamQuestionRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayExamQuestionService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    EssayExamQuestionRepository essayExamQuestionRepository;

    @GetMapping("/api/question/essay/{id}")
    public Optional<EssayExamQuestion> findQuestionById(@PathVariable("id") int id) {
        return essayExamQuestionRepository.findById(id);
    }

    @PostMapping("/api/exam/{eid}/essay")
    public EssayExamQuestion createQuestion(@PathVariable("eid") int eid, @RequestBody EssayExamQuestion question) {
        Optional<Exam> data = examRepository.findById(eid);
        if (data.isPresent()) {
            question.setId(null);
            question.setExam(data.get());
            return essayExamQuestionRepository.save(question);
        }
        return null;
    }

    @PutMapping("/api/question/essay/{id}")
    public EssayExamQuestion updateQuestion(@PathVariable("id") int id, @RequestBody EssayExamQuestion newQuestion) {
        Optional<EssayExamQuestion> data = essayExamQuestionRepository.findById(id);
        if (data.isPresent()) {
            EssayExamQuestion question = data.get();
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

            return essayExamQuestionRepository.save(question);
        }
        return null;
    }
}
