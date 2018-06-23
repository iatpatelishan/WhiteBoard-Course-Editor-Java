package webdev.models;

import lombok.Getter;
import lombok.Setter;
import webdev.models.form.BaseFormElement;

import javax.persistence.OneToMany;
import java.util.List;

public class Form extends Widget {

    @OneToMany(mappedBy = "form")
    @Getter
    @Setter
    List<BaseFormElement> elements;

    public Form() {
        super("Form");
    }
}
