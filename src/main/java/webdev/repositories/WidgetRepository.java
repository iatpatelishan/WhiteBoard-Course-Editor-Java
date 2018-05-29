package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Topic;
import webdev.models.User;
import webdev.models.Widget;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget,Integer> {
    @Query("SELECT u FROM Widget u WHERE u.topic=:topic order by u.order asc")
    List<Widget> findAllWidgetsForTopic(@Param("topic") Topic topic);
}
