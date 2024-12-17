package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.Owner;
import com.pupilmed.pupilmedapi.service.OwnerService;
import com.pupilmed.pupilmedapi.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.List;
@RestController
@RequestMapping("/owner")

public class OwnerController {


        @Autowired
        private OwnerService ownerService;

        @PostMapping("/save")
        public Owner save(@RequestBody Owner owner){
            return ownerService.save(owner);
        }
        @GetMapping("/findAll")
        public List<Owner> findAll(){
            return ownerService.findAll();
        }
        @GetMapping("/find")
        public Owner find(@RequestParam int id){
            return ownerService.findById(id);
        }
        @PutMapping("/update")
        public Owner update(@RequestBody Owner owner){
            return ownerService.update(owner);
        }
        @DeleteMapping("/delete")
        public ResponseEntity<Void> delete(@RequestParam int id){
            try {
                ownerService.delete(id);
                return ResponseEntity.ok().build();  // Status 204 No Content
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();  // Status 404 Not Found, jeśli użytkownik nie został znaleziony
            }
        }



}
