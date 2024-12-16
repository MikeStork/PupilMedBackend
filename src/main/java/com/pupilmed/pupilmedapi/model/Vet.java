package com.pupilmed.pupilmedapi.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "weterynarz")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "nazwa_kliniki")
    private String nazwaKliniki;
    @Column(name = "adres_kliniki")
    private String adresKliniki;
    @Column(name = "uzytkownikid")
    private int uzytkownikid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
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

    public int getUzytkownikid() {
        return uzytkownikid;
    }

    public void setUzytkownikid(int uzytkownikid) {
        this.uzytkownikid = uzytkownikid;
    }
}
