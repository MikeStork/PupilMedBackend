package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Pet;
import com.pupilmed.pupilmedapi.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.pupilmed.pupilmedapi.repository.VisitRepository;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet user){
        return petRepository.saveAndFlush(user);
    }
    public Pet findById(int id){
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isEmpty()) {
            throw new RuntimeException("Pet not found");
        }else{
            return pet.get();
        }
    }
    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public Pet update(Pet pet){
        Optional<Pet> found_visit = petRepository.findById(pet.getId());
        if(found_visit.isEmpty()){
            throw new RuntimeException("PET/UPDATE: Pet not found");
        }
        Pet petToUpdate = found_visit.get();
        petToUpdate.setRasa(pet.getRasa());
        petToUpdate.setGatunek(pet.getGatunek());
        petToUpdate.setUwagiOZwierzeciu(pet.getUwagiOZwierzeciu());
        petToUpdate.setWlascicielId(pet.getWlascicielId());
        petToUpdate.setDataUrodzenia(pet.getDataUrodzenia());

        return petRepository.saveAndFlush(petToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Pet> found_user = petRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("PET/UPDATE: Pet not found");
        }
        System.out.println("Attempting to delete pet with id: " + id);  // Logowanie przed usunięciem
        petRepository.deleteById(id);  // Usuwanie zwierzaka
        System.out.println("Pet with id: " + id + " has been deleted");  // Logowanie po usunięciu
        petRepository.flush();

    }
}
