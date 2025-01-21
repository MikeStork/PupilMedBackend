package com.pupilmed.fitnesse;

import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitSaveFixture {
    private String data;
    private String godzina;
    private String typWizyty;
    private int cena;
    private int weterynarzid;
    private int zwierzeid;
    private Integer zalecenieid;

    // Settery odpowiadające kolumnom w tabeli FitNesse
    public void setData(String data) {
        this.data = data;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public void setTypWizyty(String typWizyty) {
        this.typWizyty = typWizyty;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setWeterynarzid(int weterynarzid) {
        this.weterynarzid = weterynarzid;
    }

    public void setZwierzeid(int zwierzeid) {
        this.zwierzeid = zwierzeid;
    }

    public void setZalecenieid(Integer zalecenieid) {
        this.zalecenieid = zalecenieid;
    }

    // Metoda do tworzenia wizyty
    public String createVisit() {
        RestTemplate restTemplate = new RestTemplate();
        Visit visit = new Visit();
        visit.setData(LocalDate.parse(data));
        visit.setGodzina(LocalTime.parse(godzina));
        visit.setTypWizyty(typWizyty);
        visit.setCena(cena);
        visit.setWeterynarzid(weterynarzid);
        visit.setZwierzeid(zwierzeid);
        visit.setZalecenieid(zalecenieid);

        try {
            restTemplate.postForObject("http://localhost:8080/visit/save", visit, Visit.class);
            return "Success";
        } catch (Exception e) {
            return "Failure";
        }
    }
}
