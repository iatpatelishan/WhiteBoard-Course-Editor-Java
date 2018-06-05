package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Assignment;
import webdev.models.Topic;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
    @Query("SELECT u FROM Assignment u WHERE u.topic=:topic order by u.order asc")
    List<Assignment> findAllAssignmentsForTopic(@Param("topic") Topic topic);
}
