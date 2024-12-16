package com.pupilmed.pupilmedapi.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;



//@Setter
//@Getter
@Entity
@Table(name = "wizyta")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data")
    private LocalDate data;
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", example = "12:00:00")
    @Column(name = "godzina")
    private LocalTime godzina;
    @Column(name = "cena")
    private int cena;
    @Column(name = "weterynarzid")
    private int weterynarzid;
    @Column(name = "zwierzeid")
    private int zwierzeid;

    @Nullable
    @Column(name = "zalecenieid", nullable = true)
    private Integer zalecenieid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getWeterynarzid() {
        return weterynarzid;
    }

    public void setWeterynarzid(int weterynarzid) {
        this.weterynarzid = weterynarzid;
    }

    public int getZwierzeid() {
        return zwierzeid;
    }

    public void setZwierzeid(int zwierzeid) {
        this.zwierzeid = zwierzeid;
    }

    public Integer getZalecenieid() {
        return zalecenieid;
    }

    public void setZalecenieid(Integer zalecenieid) {
        this.zalecenieid = zalecenieid;
    }
}
