// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Quiz")
@Table(name = "quiz")
public class Quiz {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @Column(
            name = "id",
            nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String title;

    @NotBlank
    @Column
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Integer> answer;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "user_quiz_fk")
    )
    private AppUser user;

    @OneToMany(
            mappedBy = "quiz",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private final List<CompletedQuiz> completedQuizzes = new ArrayList<>();

    public void addCompletedQuiz(CompletedQuiz completedQuiz) {
        completedQuizzes.add(completedQuiz);
    }

}