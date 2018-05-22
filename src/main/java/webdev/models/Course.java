package webdev.models;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date modified;
}
