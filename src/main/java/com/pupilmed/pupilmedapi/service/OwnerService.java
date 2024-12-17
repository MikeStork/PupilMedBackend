package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Owner;
import com.pupilmed.pupilmedapi.model.Pet;
import com.pupilmed.pupilmedapi.repository.OwnerRepository;
import com.pupilmed.pupilmedapi.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public Owner save(Owner owner){
        return ownerRepository.save(owner);
    }
    public Owner findById(int id){
        Optional<Owner> owner = ownerRepository.findById(id);
        if(owner.isEmpty()) {
            throw new RuntimeException("OWNER/GET: Owner not found");
        }else{
            return owner.get();
        }
    }
    public List<Owner> findAll(){
        return ownerRepository.findAll();
    }
    @Transactional
    public Owner update(Owner owner){
        Optional<Owner> found_owner = ownerRepository.findById(owner.getId());
        if(found_owner.isEmpty()){
            throw new RuntimeException("OWNER/UPDATE: Owner not found");
        }
        Owner ownerToUpdate = found_owner.get();
        ownerToUpdate.setImie(owner.getImie());
        ownerToUpdate.setNazwisko(owner.getNazwisko());
        ownerToUpdate.setUzytkownikId(owner.getUzytkownikId());
        return ownerRepository.save(ownerToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Owner> found_owner = ownerRepository.findById(id);
        if(found_owner.isEmpty()){
            throw new RuntimeException("OWNER/DELETE: Owner not found");
        }
        System.out.println("Attempting to delete owner with id: " + id);  // Logowanie przed usunięciem
        ownerRepository.deleteById(id);  // Usuwanie zwierzaka
        System.out.println("Owner with id: " + id + " has been deleted");  // Logowanie po usunięciu
        ownerRepository.flush();

    }
}
