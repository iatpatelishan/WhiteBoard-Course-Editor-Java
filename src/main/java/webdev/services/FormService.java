package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.AssignmentRepository;
import webdev.repositories.ExamRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FormService {
    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/api/form")
    public Iterable<Widget> findAllForm() { return widgetRepository.findAllForm();
    }

    @GetMapping("/api/form/{id}")
    public Widget findAllForm(@PathVariable("id") Integer id) {
        return widgetRepository.findFormById(id);
    }

}
