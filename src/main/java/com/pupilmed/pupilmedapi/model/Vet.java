package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table(name = "weterynarz")
@Getter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
}
