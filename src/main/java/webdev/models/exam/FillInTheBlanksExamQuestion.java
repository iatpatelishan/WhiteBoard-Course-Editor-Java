package webdev.models.exam;

import lombok.Getter;
import lombok.Setter;

public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
    @Getter
    @Setter
    private String variables;
}
