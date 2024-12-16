package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.Pet;
import com.pupilmed.pupilmedapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @PostMapping("/save")
    public Pet save(@RequestBody Pet pet){
        return petService.save(pet);
    }
    @GetMapping("/findAll")
    public List<Pet> findAll(){
        return petService.findAll();
    }
    @GetMapping("/find")
    public Pet find(@RequestParam int id){
        return petService.findById(id);
    }
    @PutMapping("/update")
    public Pet update(@RequestBody Pet pet){
        return petService.update(pet);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id){
        try {
            petService.delete(id);
            return ResponseEntity.ok().build();  // Status 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
        }
    }

}
