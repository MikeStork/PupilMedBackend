package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.VisitType;
import com.pupilmed.pupilmedapi.service.VisitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitType")
public class VisitTypeController {
    @Autowired
    private VisitTypeService visitTypeService;

    @PostMapping("/save")
    public VisitType save(@RequestBody VisitType visitType){
        return visitTypeService.save(visitType);
    }
    @GetMapping("/findAll")
    public List<VisitType> findAll(){
        return visitTypeService.findAll();
    }
    @GetMapping("/find")
    public VisitType find(@RequestParam String visitType){
        return visitTypeService.findById(visitType);
    }
    @PutMapping("/update")
    public VisitType update(@RequestBody VisitType visitType){
        return visitTypeService.update(visitType);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam String visitType){
        try {
            visitTypeService.delete(visitType);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}