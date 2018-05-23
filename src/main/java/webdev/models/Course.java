package webdev.models;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @PrePersist
    protected void onCreate() {
        modified = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        modified = new Date();
    }
}
