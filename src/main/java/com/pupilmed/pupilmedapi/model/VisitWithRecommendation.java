package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.*;

public class VisitWithRecommendation extends Visit {
    @Transient
    private String zalecenie;

    // Getters and Setters
    public String getZalecenie() {
        return zalecenie;
    }

    public void setZalecenie(String zalecenie) {
        this.zalecenie = zalecenie;
    }
}
