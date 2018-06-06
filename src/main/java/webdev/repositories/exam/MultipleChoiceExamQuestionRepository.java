package webdev.repositories.exam;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.MultipleChoiceExamQuestion;

public interface MultipleChoiceExamQuestionRepository extends CrudRepository<MultipleChoiceExamQuestion, Integer> {

}
