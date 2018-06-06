package webdev.models.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import webdev.models.Exam;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseExamQuestion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer points;

    @Getter
    @Setter
    private String instructions;

    @Getter
    @Setter
    private String questionType;

    @ManyToOne
    @JsonIgnore
    @Getter
    @Setter
    private Exam exam;
}
