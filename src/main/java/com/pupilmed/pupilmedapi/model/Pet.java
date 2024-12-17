package com.pupilmed.pupilmedapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//@Setter
@Entity
@Table(name = "zwierze")
//@Getter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "gatunek")
    private String gatunek;
    @Column(name = "rasa")
    private String rasa;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_urodzenia")
    private LocalDate dataUrodzenia;
    @Column(name = "uwagi_o_zwierzeciu")
    private String uwagiOZwierzeciu;
    @Column(name = "wlascicielid")
    private int wlascicielId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getUwagiOZwierzeciu() {
        return uwagiOZwierzeciu;
    }

    public void setUwagiOZwierzeciu(String uwagiOZwierzeciu) {
        this.uwagiOZwierzeciu = uwagiOZwierzeciu;
    }

    public int getWlascicielId() {
        return wlascicielId;
    }

    public void setWlascicielId(int wlascicielId) {
        this.wlascicielId = wlascicielId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
}
