package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Species;
import com.pupilmed.pupilmedapi.repository.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public SpeciesService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    @Transactional
    public Species save(Species species){
        return speciesRepository.saveAndFlush(species);
    }
    public Species findById(int id){
        Optional<Species> species = speciesRepository.findById(id);
        if(species.isEmpty()) {
            throw new RuntimeException("SPECIES/GET: Species not found");
        }else{
            return species.get();
        }
    }
    public List<Species> findAll(){
        return speciesRepository.findAll();
    }

    @Transactional
    public Species update(Species species){
        Optional<Species> found_species = speciesRepository.findById(species.getId());
        if(found_species.isEmpty()){
            throw new RuntimeException("SPECIES/UPDATE: Species not found");
        }
        Species speciesToUpdate = found_species.get();
        speciesToUpdate.setSpecies(species.getSpecies());
        speciesToUpdate.setBreed(species.getBreed());

        return speciesRepository.save(speciesToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Species> found_species = speciesRepository.findById(id);
        if(found_species.isEmpty()){
            throw new RuntimeException("SPECIES/DELETE: Species not found");
        }
        System.out.println("Attempting to delete species with id: " + id);  // Logowanie przed usunięciem
        speciesRepository.deleteById(id);  // Usuwanie gatunku
        System.out.println("Species with id: " + id + " has been deleted");  // Logowanie po usunięciu
        speciesRepository.flush();

    }
}
