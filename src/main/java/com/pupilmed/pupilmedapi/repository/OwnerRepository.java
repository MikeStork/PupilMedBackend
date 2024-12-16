package com.pupilmed.pupilmedapi.repository;

import com.pupilmed.pupilmedapi.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Integer> {
}
