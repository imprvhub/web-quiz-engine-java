// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Answer(List<Integer> answer) {
    public Answer(List<Integer> answer) {
        if (Objects.isNull(answer)) {
            this.answer = Collections.emptyList();
        } else {
            this.answer = answer;
        }
    }
}