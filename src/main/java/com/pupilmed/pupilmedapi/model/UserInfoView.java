package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "informacje_o_uzytkownikach")
public class UserInfoView {
    @Id
    @Column(name = "id_uzytkownika")
    private Integer idUzytkownika;

    @Size(max = 12)
    @Column(name = "numer_telefonu", length = 12)
    private String numerTelefonu;

    @Column(name = "imie", length = Integer.MAX_VALUE)
    private String imie;

    @Column(name = "nazwisko", length = Integer.MAX_VALUE)
    private String nazwisko;

    @Size(max = 10)
    @Column(name = "typ_uzytkownika", length = 10)
    private String typUzytkownika;

    @Column(name = "id_wlasciciela")
    private Integer idWlasciciela;

    @Column(name = "id_weterynarza")
    private Integer idWeterynarza;

    @Column(name = "nazwa_kliniki", length = Integer.MAX_VALUE)
    private String nazwaKliniki;

    @Column(name = "adres_kliniki", length = Integer.MAX_VALUE)
    private String adresKliniki;

    public String getAdresKliniki() {
        return adresKliniki;
    }

    public String getNazwaKliniki() {
        return nazwaKliniki;
    }

    public Integer getIdWeterynarza() {
        return idWeterynarza;
    }

    public Integer getIdWlasciciela() {
        return idWlasciciela;
    }

    public String getTypUzytkownika() {
        return typUzytkownika;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public Integer getIdUzytkownika() {
        return idUzytkownika;
    }
}
