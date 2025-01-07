package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
@Table(name = "informacje_o_wizycie")
public class AdminVisitInfoView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wizyta_id")
    private Integer id;

    @Column(name = "godzina")
    private LocalTime godzina;

    @Size(max = 255)
    @Column(name = "typ_wizyty")
    private String typWizyty;

    @Column(name = "wlasciciel", length = Integer.MAX_VALUE)
    private String wlasciciel;

    @Column(name = "weterynarz", length = Integer.MAX_VALUE)
    private String weterynarz;

    @Size(max = 12)
    @Column(name = "numer_wlasciciela", length = 12)
    private String numerWlasciciela;

    @Size(max = 12)
    @Column(name = "numer_weterynarza", length = 12)
    private String numerWeterynarza;

    @Column(name = "cena")
    private Integer cena;

    @Column(name = "zwierze", length = Integer.MAX_VALUE)
    private String zwierze;

    @Size(max = 255)
    @Column(name = "rasa")
    private String rasa;

    @Column(name = "wiek")
    private Integer wiek;

    @Column(name = "zalecenia", length = Integer.MAX_VALUE)
    private String zalecenia;

    @Column(name = "zalecenieid")
    private Integer zalecenieid;

    @Column(name = "wlasciciel_id")
    private Integer wlascicielId;

    @Column(name = "weterynarz_id")
    private Integer weterynarzId;

    @Column(name = "zwierze_id")
    private Integer zwierzeId;

    public Integer getZwierzeId() {
        return zwierzeId;
    }

    public Integer getWeterynarzId() {
        return weterynarzId;
    }

    public Integer getWlascicielId() {
        return wlascicielId;
    }

    public Integer getZalecenieid() {
        return zalecenieid;
    }

    public String getZalecenia() {
        return zalecenia;
    }

    public Integer getWiek() {
        return wiek;
    }

    public String getRasa() {
        return rasa;
    }

    public String getZwierze() {
        return zwierze;
    }

    public Integer getCena() {
        return cena;
    }

    public String getNumerWeterynarza() {
        return numerWeterynarza;
    }

    public String getNumerWlasciciela() {
        return numerWlasciciela;
    }

    public String getWeterynarz() {
        return weterynarz;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public String getTypWizyty() {
        return typWizyty;
    }

    public LocalTime getGodzina() {
        return godzina;
    }

    public Integer getId() {
        return id;
    }
}
