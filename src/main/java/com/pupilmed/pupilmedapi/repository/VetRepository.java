package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Vet, Integer> {
}
