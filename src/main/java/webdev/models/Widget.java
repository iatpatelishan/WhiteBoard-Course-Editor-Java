package webdev.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int order;

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
    private String type;

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

    /*@Getter
    @Setter
    private ListType listType;*/
}
