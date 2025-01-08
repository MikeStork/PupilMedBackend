package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitViewInfoRepository extends JpaRepository<VisitInfoView, Integer> {

    List<VisitInfoView> findAllByWlascicielIdAndDataBetween(Integer wlascicielId, LocalDate dataAfter, LocalDate dataBefore);
}
