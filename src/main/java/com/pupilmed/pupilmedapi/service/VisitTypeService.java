package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.VisitType;
import com.pupilmed.pupilmedapi.repository.VisitTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitTypeService {
    private final VisitTypeRepository visitTypeRepository;

    public VisitTypeService(VisitTypeRepository visitTypeRepository) {
        this.visitTypeRepository = visitTypeRepository;
    }

    @Transactional
    public VisitType save(VisitType visitType){
        return visitTypeRepository.saveAndFlush(visitType);
    }
    public VisitType findById(String  visitTypeName){
        Optional<VisitType> visitType = visitTypeRepository.findById(visitTypeName);
        if(visitType.isEmpty()) {
            throw new RuntimeException("VISITTYPE/GET: VisitType not found");
        }else{
            return visitType.get();
        }
    }
    public List<VisitType> findAll(){
        return visitTypeRepository.findAll();
    }

    @Transactional
    public VisitType update(VisitType visitType){
        Optional<VisitType> found_visitType = visitTypeRepository.findById(visitType.getTypWizyty());
        if(found_visitType.isEmpty()){
            throw new RuntimeException("VISITTYPE/UPDATE: VisitType not found");
        }
        VisitType visitTypeToUpdate = found_visitType.get();
        visitTypeToUpdate.setKoszt(visitType.getKoszt());

        return visitTypeRepository.save(visitTypeToUpdate);
    }
    @Transactional
    public void delete(String visitType){
        Optional<VisitType> found_visitType = visitTypeRepository.findById( visitType);
        if(found_visitType.isEmpty()){
            throw new RuntimeException("VISITTYPE/DELETE: VisitType not found");
        }
        System.out.println("Attempting to delete visitType with id: " +  visitType);  // Logowanie przed usunięciem
        visitTypeRepository.deleteById( visitType);  // Usuwanie gatunku
        System.out.println("VisitType with id: " +  visitType + " has been deleted");  // Logowanie po usunięciu
        visitTypeRepository.flush();

    }
}
