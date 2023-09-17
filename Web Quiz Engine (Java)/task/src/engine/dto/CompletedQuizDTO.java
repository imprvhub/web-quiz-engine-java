// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.dto;

import java.util.Date;

public record CompletedQuizDTO(Long id, Date completedAt) {
}