package webdev.models.exam;

import lombok.Getter;
import lombok.Setter;
import webdev.models.Exam;

public class BaseExamQuestion {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String instructions;

    @Getter
    @Setter
    private String typeofQuestion;

    @Getter
    @Setter
    private Exam exam;
}
