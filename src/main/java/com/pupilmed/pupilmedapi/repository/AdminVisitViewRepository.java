package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.AdminVisitView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AdminVisitViewRepository extends JpaRepository<AdminVisitView, Integer> {
    List<AdminVisitView> findAllByDataBetween(LocalDate dataAfter, LocalDate dataBefore);
}
