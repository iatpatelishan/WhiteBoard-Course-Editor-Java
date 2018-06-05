package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Exam extends Widget {

    public Exam() {
        super("Exam");
    }
}
