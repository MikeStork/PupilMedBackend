package com.pupilmed.pupilmedapi.service.facades;


import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;

import java.time.LocalDate;
import java.util.List;

public interface VisitServiceFacade {
    Visit save(Visit visit);
    Visit findById(int id);
    List<Visit> findAll();
    Visit update(Visit visit);
    void delete(int id);
    List<Visit> findAllForVet(Integer vetid);
    List<Visit> findAllForVetBetweenDates(Integer vetid, LocalDate from, LocalDate to);
    List<VisitView> findAllVisitViewsBetweenDates(LocalDate from, LocalDate to);
    List<VisitInfoView> findAllVisitsByUseridBetweenDates(Integer userid, LocalDate from, LocalDate to);
    List<Visit> findAllBetweenDates(LocalDate from, LocalDate to);
}
