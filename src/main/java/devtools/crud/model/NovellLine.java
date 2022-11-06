package devtools.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class NovellLine {
    @Id
    private Long id;
    private String speaker;
    private String light;
    private String emotion;
    private String text;
    private String background;
    private String character1;
    private String character2;
    private String character3;
    private String music;
    private boolean autoskip;
    private int noskipif;
    private boolean choice;
    private String option1;
    private int nextscene1;
    private String option2;
    private int nextscene2;
    private String option3;
    private int nextscene3;

}
