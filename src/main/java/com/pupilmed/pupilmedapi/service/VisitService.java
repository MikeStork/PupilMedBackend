package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit user){
        return visitRepository.saveAndFlush(user);
    }
    public Visit findById(int id){
        Optional<Visit> user = visitRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("Visit not found");
        }else{
            return user.get();
        }
    }
    public List<Visit> findAll(){
        return visitRepository.findAll();
    }

    public Visit update(Visit visit){
        Optional<Visit> found_visit = visitRepository.findById(visit.getId());
        if(found_visit.isEmpty()){
            throw new RuntimeException("USER/UPDATE: Visit not found");
        }
        Visit visitToUpdate = found_visit.get();
        visitToUpdate.setCena(visit.getCena());
        visitToUpdate.setData(visit.getData());
        visitToUpdate.setGodzina(visit.getGodzina());
        visitToUpdate.setWeterynarzid(visit.getWeterynarzid());
        visitToUpdate.setZwierzeid(visit.getZwierzeid());
        visitToUpdate.setZalecenieid(visit.getZalecenieid());

        return visitRepository.saveAndFlush(visitToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Visit> found_user = visitRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/UPDATE: Visit not found");
        }
        System.out.println("Attempting to delete user with id: " + id);  // Logowanie przed usunięciem
        visitRepository.deleteById(id);  // Usuwanie wizyty
        System.out.println("Visit with id: " + id + " has been deleted");  // Logowanie po usunięciu
        visitRepository.flush();

    }
}
