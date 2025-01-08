package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;
import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewInfoRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewRepository;
import com.pupilmed.pupilmedapi.repository.VisitRepository;
import com.pupilmed.pupilmedapi.service.factories.StandardVisitFactory;
import com.pupilmed.pupilmedapi.service.factories.VisitWithRecommmendationFactory;
import com.pupilmed.pupilmedapi.service.filterStrategy.DateRangeVisitFilterStrategy;
import com.pupilmed.pupilmedapi.service.filterStrategy.DefaultVisitFilterStrategy;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final VisitViewRepository visitViewRepository;
    private final VisitViewInfoRepository visitViewInfoRepository;
    private final RecommendationRepository recommendationRepository;


    public VisitService(VisitRepository visitRepository, VisitViewRepository visitViewRepository, VisitViewInfoRepository visitViewInfoRepository, RecommendationRepository recommendationRepository) {
        this.visitRepository = visitRepository;
        this.visitViewRepository = visitViewRepository;
        this.visitViewInfoRepository = visitViewInfoRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Transactional
    public Visit save(Visit visit) {
//        if (visit.getId() != null && visitRepository.existsById(visit.getId())) {
//            return visitRepository.save(visit);
//        } else {
//            // Nowa encja
//            visit.setId(null);
//        }
            return visitRepository.save(visit);
    }
    public Visit findById(int id){
        Optional<Visit> visit = visitRepository.findById(id);
        if(visit.isEmpty()) {
            throw new RuntimeException("VISIT/GET: Visit not found");
        }
        Visit found_visit = visit.get();
        if(found_visit.getZalecenieid() == null) {
            StandardVisitFactory svf = new StandardVisitFactory();
            return svf.createVisit(found_visit);
        }else {
            VisitWithRecommmendationFactory vwrf = new VisitWithRecommmendationFactory(recommendationRepository);
            return vwrf.createVisit(found_visit);
        }

    }
    public List<Visit> findAll(){
//        return visitRepository.findAll();
        List<Visit> visits = visitRepository.findAll();
        List<Visit> mutableVisits = new ArrayList<>(visits);
        DefaultVisitFilterStrategy dvfs = new DefaultVisitFilterStrategy();
        return dvfs.filterVisits(mutableVisits, List.of());
    }

    @Transactional
    public Visit update(Visit visit){
        Optional<Visit> found_visit = visitRepository.findById(visit.getId());
        if(found_visit.isEmpty()){
            throw new RuntimeException("VISIT/UPDATE: Visit not found");
        }
        Visit visitToUpdate = found_visit.get();
        visitToUpdate.setCena(visit.getCena());
        visitToUpdate.setData(visit.getData());
        visitToUpdate.setGodzina(visit.getGodzina());
        visitToUpdate.setWeterynarzid(visit.getWeterynarzid());
        visitToUpdate.setZwierzeid(visit.getZwierzeid());
        visitToUpdate.setZalecenieid(visit.getZalecenieid());
        visitToUpdate.setTypWizyty(visit.getTypWizyty());
        return visitRepository.save(visitToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Visit> found_visit = visitRepository.findById(id);
        if(found_visit.isEmpty()){
            throw new RuntimeException("VISIT/DELETE: Visit not found");
        }
        System.out.println("Attempting to delete visit with id: " + id);  // Logowanie przed usunięciem
        visitRepository.deleteById(id);  // Usuwanie wizyty
        System.out.println("Visit with id: " + id + " has been deleted");  // Logowanie po usunięciu
        visitRepository.flush();

    }

    public List<Visit> findAllForVet(Integer vetid) {
        return visitRepository.findAllByWeterynarzidEquals(vetid);
    }


    public List<Visit> findAllForVetBetweenDates(Integer vetid, LocalDate from, LocalDate to) {
        return visitRepository.findAllByDataBetweenAndWeterynarzidEquals(from, to, vetid);
    }

    public List<VisitView> findAllVisitViewsBetweenDates(LocalDate from, LocalDate to) {
        return visitViewRepository.findAllByDataBetween(from, to);
    }

    public List<VisitInfoView> findAllVisitsByUseridBetweenDates(Integer userid, LocalDate from, LocalDate to) {
        return visitViewInfoRepository.findAllByWlascicielIdAndDataBetween(userid, from, to);
    }
    public List<Visit> findAllBetweenDates(LocalDate from, LocalDate to) {
        List<Visit> visits = visitRepository.findAll();
        List<Visit> mutableVisits = new ArrayList<>(visits);
        DateRangeVisitFilterStrategy drvfs = new DateRangeVisitFilterStrategy();
        return drvfs.filterVisits(mutableVisits, List.of(from,to));
    }

}
