package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "typ_wizyty")
public class VisitType {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Id
    @Column(name = "typ_wizyty")
    private String typWizyty;
    @Column(name = "koszt")
    private int koszt;

    public String getTypWizyty() {
        return typWizyty;
    }

    public void setTypWizyty(String typWizyty) {
        this.typWizyty = typWizyty;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }
}
