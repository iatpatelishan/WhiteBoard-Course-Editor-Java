package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev.models.Assignment;
import webdev.models.Topic;
import webdev.models.form.BaseFormElement;

import java.util.List;

public interface BaseFormElementRepository extends CrudRepository<BaseFormElement, Integer> {

}
