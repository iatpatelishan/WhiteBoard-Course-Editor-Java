package webdev.repositories.exam;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.TrueOrFalseExamQuestion;

public interface TrueOrFalseExamQuestionRepository extends CrudRepository<TrueOrFalseExamQuestion, Integer> {

}
