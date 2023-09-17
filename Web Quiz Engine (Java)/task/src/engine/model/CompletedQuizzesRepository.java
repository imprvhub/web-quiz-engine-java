// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.repository;

import engine.model.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizzesRepository
        extends CrudRepository<CompletedQuiz, Long>, PagingAndSortingRepository<CompletedQuiz, Long> {

    @Query("SELECT cq FROM CompletedQuiz cq WHERE cq.user.username = :username ORDER BY cq.completedAt DESC")
    Page<CompletedQuiz> findAll(@Param("username") String username, Pageable pageable);

}