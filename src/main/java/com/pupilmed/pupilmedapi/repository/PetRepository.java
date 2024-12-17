package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
