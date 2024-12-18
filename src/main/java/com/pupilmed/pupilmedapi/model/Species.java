package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gatunek_rasa")
public class Species{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "gatunek")
    private String species;
    @Column(name = "rasa")
    private String breed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
