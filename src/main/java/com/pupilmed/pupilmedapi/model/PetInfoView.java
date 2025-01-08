package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


@Entity
@Table(name = "informacje_o_zwierzaku")
public class PetInfoView {
    @Id
    @Column(name = "id_zwierzecia")
    private Integer id;

    @Column(name = "id_wlasciciela")
    private Integer idWlasciciela;

    @Column(name = "wlasciciel", length = Integer.MAX_VALUE)
    private String wlasciciel;

    @Size(max = 12)
    @Column(name = "numer_telefonu_wlasciciela", length = 12)
    private String numerTelefonuWlasciciela;

    @Size(max = 100)
    @Column(name = "imie_zwierzecia", length = 100)
    private String imieZwierzecia;

    @Column(name = "wiek")
    private Integer wiek;

    @Size(max = 255)
    @Column(name = "rasa")
    private String rasa;

    @Column(name = "dodatkowe_informacje", length = Integer.MAX_VALUE)
    private String dodatkoweInformacje;

    @Column(name = "data_urodzenia")
    private LocalDate dataUrodzenia;

    @Size(max = 100)
    @Column(name = "typ_zwierzeca", length = 100)
    private String typZwierzeca;

    public String getTypZwierzeca() {
        return typZwierzeca;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getDodatkoweInformacje() {
        return dodatkoweInformacje;
    }

    public String getRasa() {
        return rasa;
    }


    public Integer getWiek() {
        return wiek;
    }

    public String getImieZwierzecia() {
        return imieZwierzecia;
    }

    public String getNumerTelefonuWlasciciela() {
        return numerTelefonuWlasciciela;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public Integer getIdWlasciciela() {
        return idWlasciciela;
    }

    public Integer getId() {
        return id;
    }
}
