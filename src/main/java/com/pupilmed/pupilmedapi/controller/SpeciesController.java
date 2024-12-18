package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.Species;
import com.pupilmed.pupilmedapi.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/species")
public class SpeciesController {
        @Autowired
        private SpeciesService speciesService;

        @PostMapping("/save")
        public Species save(@RequestBody Species species){
            return speciesService.save(species);
        }
        @GetMapping("/findAll")
        public List<Species> findAll(){
            return speciesService.findAll();
        }
        @GetMapping("/find")
        public Species find(@RequestParam int id){
            return speciesService.findById(id);
        }
        @PutMapping("/update")
        public Species update(@RequestBody Species species){
            return speciesService.update(species);
        }
        @DeleteMapping("/delete")
        public ResponseEntity<Void> delete(@RequestParam int id){
            try {
                speciesService.delete(id);
                return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

    }


