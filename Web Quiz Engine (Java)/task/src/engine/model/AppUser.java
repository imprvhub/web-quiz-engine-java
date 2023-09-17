// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "user")
public class AppUser {

    @Id
    @Column(
            name = "id",
            nullable = false
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column
    private String username;

    @Email
    @Column
    private String email;

    @NotBlank
    @Column
    private String password;

    @Column
    private String authority;

    @OneToMany(mappedBy = "user")
    List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private final List<CompletedQuiz> completedQuizzes = new ArrayList<>();

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }
    public void addCompletedQuiz(CompletedQuiz completedQuiz) {
        this.completedQuizzes.add(completedQuiz);
    }

    public AppUser(String username, String email, String password, String authority) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}