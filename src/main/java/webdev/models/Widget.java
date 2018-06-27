package webdev.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import webdev.models.form.BaseFormElement;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Widget {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @Column(name="widgetOrder")
    private Integer order;

    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private String className;

    @Getter
    @Setter
    private String style;

    @Getter
    @Setter
    private String width;

    @Getter
    @Setter
    private String height;

    @Getter
    @Setter
    private String widgetType;

    @Getter
    @Setter
    private Integer size;

    @Getter
    @Setter
    private String href;

    @Getter
    @Setter
    private String src;

    @Getter
    @Setter
    private String listItems;

    @Getter
    @Setter
    private ListType listType;

    @OneToMany(mappedBy = "widget")
    @Getter
    @Setter
    List<BaseFormElement> elements;

    @Getter
    @Setter
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Topic topic;

    public Widget() {
        super();
    }

    public Widget(String widgetType) {
        super();
        this.widgetType = widgetType;
    }
}
