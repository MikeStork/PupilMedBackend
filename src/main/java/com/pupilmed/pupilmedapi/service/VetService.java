package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Vet;
import com.pupilmed.pupilmedapi.repository.VetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class VetService {
    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }


    public Vet save(Vet user){
        return vetRepository.saveAndFlush(user);
    }
    public Vet findById(int id){
        Optional<Vet> vet = vetRepository.findById(id);
        if(vet.isEmpty()) {
            throw new RuntimeException("Visit not found");
        }else{
            return vet.get();
        }
    }
    public List<Vet> findAll(){
        return vetRepository.findAll();
    }

    public Vet update(Vet vet){
        Optional<Vet> found_vet = vetRepository.findById(vet.getId());
        if(found_vet.isEmpty()){
            throw new RuntimeException("VET/UPDATE: Vet not found");
        }
        Vet vetToUpdate = found_vet.get();
        vetToUpdate.setImie(vet.getImie());
        vetToUpdate.setNazwisko(vet.getNazwisko());
        vetToUpdate.setNazwaKliniki(vet.getNazwaKliniki());
        vetToUpdate.setAdresKliniki(vet.getAdresKliniki());
        vetToUpdate.setUzytkownikid(vet.getUzytkownikid());

        return vetRepository.saveAndFlush(vetToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Vet> found_user = vetRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("VET/DELETE: Vet not found");
        }
        System.out.println("Attempting to delete user with id: " + id);  // Logowanie przed usunięciem
        vetRepository.deleteById(id);  // Usuwanie zwierzaka
        System.out.println("Vet with id: " + id + " has been deleted");  // Logowanie po usunięciu
        vetRepository.flush();

    }
}
