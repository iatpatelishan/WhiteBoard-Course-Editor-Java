package webdev.repositories.exam;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.FillInTheBlanksExamQuestion;

public interface FillInTheBlanksExamQuestionRepository extends CrudRepository<FillInTheBlanksExamQuestion, Integer> {

}
