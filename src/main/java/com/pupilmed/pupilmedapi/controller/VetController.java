package com.pupilmed.pupilmedapi.controller;


import com.pupilmed.pupilmedapi.model.Vet;
import com.pupilmed.pupilmedapi.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vet")
public class VetController {
    @Autowired
    private VetService vetService;

    @PostMapping("/save")
    public Vet save(@RequestBody Vet vet){
        return vetService.save(vet);
    }
    @GetMapping("/findAll")
    public List<Vet> findAll(){
        return vetService.findAll();
    }
    @GetMapping("/find")
    public Vet find(@RequestParam int id){
        return vetService.findById(id);
    }
    @PutMapping("/update")
    public Vet update(@RequestBody Vet vet){
        return vetService.update(vet);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id){
        try {
            vetService.delete(id);
            return ResponseEntity.ok().build();  // Status 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
        }
    }

}
