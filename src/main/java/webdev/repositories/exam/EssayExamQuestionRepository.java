package webdev.repositories.exam;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.EssayExamQuestion;

public interface EssayExamQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {

}
