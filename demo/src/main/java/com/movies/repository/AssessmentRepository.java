package com.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movies.model.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

	Page<Assessment> findByMovieId(Long movieId, Pageable pagination);

    @Query(value = "select avg(a.assessment) from Assessments a where a.movie_id = ?1", nativeQuery = true)
    float calculateAverageOfEvaluations(Long movieId);
}
