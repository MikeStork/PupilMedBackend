package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @PostMapping("/save")
    public Visit save(@RequestBody Visit visit){
        return visitService.save(visit);
    }
    @GetMapping("/findAll")
    public List<Visit> findAll(){
        return visitService.findAll();
    }
    @GetMapping("/find")
    public Visit find(@RequestParam int id){
        return visitService.findById(id);
    }
    @PutMapping("/update")
    public Visit update(@RequestBody Visit visit){
        return visitService.update(visit);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id){
        try {
            visitService.delete(id);
            return ResponseEntity.ok().build();  // Status 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
        }
    }

}
