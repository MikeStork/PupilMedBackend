package com.pupilmed.pupilmedapi.controller;


import com.pupilmed.pupilmedapi.model.Recommendation;
import com.pupilmed.pupilmedapi.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/save")
    public Recommendation save(@RequestBody Recommendation recommendation){
        return recommendationService.save(recommendation);
    }
    @GetMapping("/findAll")
    public List<Recommendation> findAll(){
        return recommendationService.findAll();
    }
    @GetMapping("/find")
    public Recommendation find(@RequestParam int id){
        return recommendationService.findById(id);
    }
    @PutMapping("/update")
    public Recommendation update(@RequestBody Recommendation recommendation){
        return recommendationService.update(recommendation);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id){
        try {
            recommendationService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

