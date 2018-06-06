package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import webdev.models.exam.BaseExamQuestion;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Exam extends Widget {

    @OneToMany(mappedBy = "exam")
    @Getter
    @Setter
    List<BaseExamQuestion> questions;

    public Exam() {
        super("Exam");
    }
}
