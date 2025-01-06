package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
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
        }else{
            return visit.get();
        }
    }
    public List<Visit> findAll(){
        return visitRepository.findAll();
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
}
