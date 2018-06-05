package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @ManyToOne
    @JsonIgnore
    @Getter
    @Setter
    @NotFound(action = NotFoundAction.IGNORE)
    private Course course;

    @Getter
    @Setter
    @OneToMany(mappedBy="module")
    private List<Lesson> lessons;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date modified;

    @PrePersist
    protected void onCreate() {
        modified = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modified = new Date();
    }
}
