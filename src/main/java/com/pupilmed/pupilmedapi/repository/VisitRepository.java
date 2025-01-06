package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findAllByWeterynarzidEquals(Integer vetid);

    List<Visit> findAllByDataBetweenAndWeterynarzidEquals( LocalDate from, LocalDate to,Integer vetid);
}
