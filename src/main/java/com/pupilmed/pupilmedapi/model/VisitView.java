package com.pupilmed.pupilmedapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "wizyty_administrator")
public class VisitView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data")
    private LocalDate data;
    @Column(name = "godzina")
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "12:00:00")
    private LocalTime godzina;
    @Column(name = "imie_weterynarza")
    private String imieWeterynarza;
    @Column(name = "nazwisko_weterynarza")
    private String nazwiskoWeterynarza;
    @Column(name = "nazwa_kliniki")
    private String nazwaKliniki;
    @Column(name = "adres_kliniki")
    private String adresKliniki;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public void setGodzina(LocalTime godzina) {
        this.godzina = godzina;
    }

    public String getImieWeterynarza() {
        return imieWeterynarza;
    }

    public void setImieWeterynarza(String imieWeterynarza) {
        this.imieWeterynarza = imieWeterynarza;
    }

    public String getNazwiskoWeterynarza() {
        return nazwiskoWeterynarza;
    }

    public void setNazwiskoWeterynarza(String nazwiskoWeterynarza) {
        this.nazwiskoWeterynarza = nazwiskoWeterynarza;
    }

    public String getNazwaKliniki() {
        return nazwaKliniki;
    }

    public void setNazwaKliniki(String nazwaKliniki) {
        this.nazwaKliniki = nazwaKliniki;
    }

    public String getAdresKliniki() {
        return adresKliniki;
    }

    public void setAdresKliniki(String adresKliniki) {
        this.adresKliniki = adresKliniki;
    }
}
