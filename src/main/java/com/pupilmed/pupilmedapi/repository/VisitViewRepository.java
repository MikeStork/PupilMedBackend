package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.VisitView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface VisitViewRepository extends JpaRepository<VisitView, Integer> {
    List<VisitView> findAllByDataBetween(LocalDate dataAfter, LocalDate dataBefore);
}
