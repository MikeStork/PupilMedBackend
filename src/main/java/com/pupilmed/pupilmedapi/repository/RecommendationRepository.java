package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
