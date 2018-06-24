package webdev.models.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import webdev.models.Widget;

import javax.persistence.*;
@Entity
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

    @Getter
    @Setter
    private String elementType;

    @Getter
    @Setter
    private Number orderno;

    @ManyToOne
    @Getter
    @Setter
    @JsonIgnore
    private Widget widget;
}
