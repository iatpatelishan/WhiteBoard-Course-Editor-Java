package webdev.models.form;

import lombok.Getter;
import lombok.Setter;
import webdev.models.Form;

import javax.persistence.*;

@Inheritance(strategy=InheritanceType.JOINED)
public class BaseFormElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String cssClass;

    @Getter
    @Setter
    private String cssStyle;

    @Getter
    @Setter
    private String label;

    @Getter
    @Setter
    private String labelDirection;

    @ManyToOne
    @Getter
    @Setter
    private Form form;
}
