package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import com.pupilmed.pupilmedapi.repository.VisitRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewInfoRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewRepository;
import com.pupilmed.pupilmedapi.service.facades.VisitServiceFacade;
import com.pupilmed.pupilmedapi.service.factories.StandardVisitFactory;
import com.pupilmed.pupilmedapi.service.factories.VisitWithRecommmendationFactory;
import com.pupilmed.pupilmedapi.service.filterStrategy.DateRangeVisitFilterStrategy;
import com.pupilmed.pupilmedapi.service.filterStrategy.DefaultVisitFilterStrategy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService implements VisitServiceFacade {
    private final VisitRepository visitRepository;
    private final VisitViewRepository visitViewRepository;
    private final VisitViewInfoRepository visitViewInfoRepository;
    private final RecommendationRepository recommendationRepository;

    public VisitService(VisitRepository visitRepository, VisitViewRepository visitViewRepository,
                        VisitViewInfoRepository visitViewInfoRepository, RecommendationRepository recommendationRepository) {
        this.visitRepository = visitRepository;
        this.visitViewRepository = visitViewRepository;
        this.visitViewInfoRepository = visitViewInfoRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Transactional
    public Visit save(Visit visit) {
        try {
            return visitRepository.save(visit);
        } catch (Exception e) {
            throw new RuntimeException("VISIT/SAVE: Error while saving visit", e);
        }
    }

    public Visit findById(int id) {
        return visitRepository.findById(id)
                .map(visit -> {
                    if (visit.getZalecenieid() == null) {
                        return new StandardVisitFactory().createVisit(visit);
                    } else {
                        return new VisitWithRecommmendationFactory(recommendationRepository).createVisit(visit);
                    }
                })
                .orElseThrow(() -> new RuntimeException("VISIT/GET: Visit not found with id " + id));
    }

    public List<Visit> findAll() {
        try {
            List<Visit> visits = visitRepository.findAll();
            return new DefaultVisitFilterStrategy().filterVisits(new ArrayList<>(visits), List.of());
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_ALL: Error while retrieving visits", e);
        }
    }

    @Transactional
    public Visit update(Visit visit) {
        return visitRepository.findById(visit.getId())
                .map(existingVisit -> {
                    existingVisit.setCena(visit.getCena());
                    existingVisit.setData(visit.getData());
                    existingVisit.setGodzina(visit.getGodzina());
                    existingVisit.setWeterynarzid(visit.getWeterynarzid());
                    existingVisit.setZwierzeid(visit.getZwierzeid());
                    existingVisit.setZalecenieid(visit.getZalecenieid());
                    existingVisit.setTypWizyty(visit.getTypWizyty());
                    return visitRepository.save(existingVisit);
                })
                .orElseThrow(() -> new RuntimeException("VISIT/UPDATE: Visit not found with id " + visit.getId()));
    }

    @Transactional
    public void delete(int id) {
        if (!visitRepository.existsById(id)) {
            throw new RuntimeException("VISIT/DELETE: Visit not found with id " + id);
        }
        try {
            visitRepository.deleteById(id);
            visitRepository.flush();
        } catch (Exception e) {
            throw new RuntimeException("VISIT/DELETE: Error while deleting visit with id " + id, e);
        }
    }

    public List<Visit> findAllForVet(Integer vetid) {
        try {
            return visitRepository.findAllByWeterynarzidEquals(vetid);
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_FOR_VET: Error while retrieving visits for vet id " + vetid, e);
        }
    }

    public List<Visit> findAllForVetBetweenDates(Integer vetid, LocalDate from, LocalDate to) {
        try {
            return visitRepository.findAllByDataBetweenAndWeterynarzidEquals(from, to, vetid);
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_FOR_VET_DATES: Error while retrieving visits for vet id " + vetid + " between " + from + " and " + to, e);
        }
    }

    public List<VisitView> findAllVisitViewsBetweenDates(LocalDate from, LocalDate to) {
        try {
            return visitViewRepository.findAllByDataBetween(from, to);
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_VIEWS_BETWEEN_DATES: Error while retrieving visit views", e);
        }
    }

    public List<VisitInfoView> findAllVisitsByUseridBetweenDates(Integer userid, LocalDate from, LocalDate to) {
        try {
            return visitViewInfoRepository.findAllByWlascicielIdAndDataBetween(userid, from, to);
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_BY_USER_BETWEEN_DATES: Error while retrieving visits for user id " + userid, e);
        }
    }

    public List<Visit> findAllBetweenDates(LocalDate from, LocalDate to) {
        try {
            List<Visit> visits = visitRepository.findAll();
            return new DateRangeVisitFilterStrategy().filterVisits(new ArrayList<>(visits), List.of(from, to));
        } catch (Exception e) {
            throw new RuntimeException("VISIT/GET_BETWEEN_DATES: Error while retrieving visits between " + from + " and " + to, e);
        }
    }
}
