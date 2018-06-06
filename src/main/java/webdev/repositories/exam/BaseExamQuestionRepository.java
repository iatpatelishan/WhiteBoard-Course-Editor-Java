package webdev.repositories.exam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.exam.BaseExamQuestion;

import java.util.List;

public interface BaseExamQuestionRepository extends CrudRepository<BaseExamQuestion, Integer> {

}
