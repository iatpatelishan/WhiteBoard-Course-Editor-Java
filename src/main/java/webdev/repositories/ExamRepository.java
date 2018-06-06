package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Exam;
import webdev.models.Topic;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Integer> {
    @Query("SELECT u FROM Exam u WHERE u.topic=:topic order by u.order asc")
    List<Exam> findAllExamsForTopic(@Param("topic") Topic topic);
}
