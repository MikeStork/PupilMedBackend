package com.pupilmed.fitnesse;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.service.filterStrategy.DateRangeVisitFilterStrategy;
import fit.RowFixture;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateRangeVisitFilterFixture extends RowFixture {
    private List<Visit> filteredVisits;
    public DateRangeVisitFilterFixture() {
        // Konstruktor bezparametrowy wymagany przez FitNesse
    }
    @Override
    public Object[] query() throws Exception {
        // Przykładowe dane wizyt
        List<Visit> visits = new ArrayList<>();
        visits.add(createVisit(1, "Konsultacja", LocalDate.of(2025, 1, 1), 100, 1, 1, null));
        visits.add(createVisit(2, "Kontrola", LocalDate.of(2025, 1, 10), 200, 1, 2, null));
        visits.add(createVisit(3, "Operacja", LocalDate.of(2025, 2, 1), 300, 2, 3, 1));

        // Przykładowe parametry daty
        List<Object> params = Arrays.asList(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));

        // Inicjalizacja strategii
        DateRangeVisitFilterStrategy strategy = new DateRangeVisitFilterStrategy();

        // Filtrowanie wizyt
        filteredVisits = strategy.filterVisits(visits, params);

        return filteredVisits.toArray();
    }

    @Override
    public Class<?> getTargetClass() {
        return Visit.class;
    }

    // Tworzenie przykładowej wizyty
    private Visit createVisit(int id, String typWizyty, LocalDate data, int cena, int weterynarzid, int zwierzeid, Integer zalecenieid) {
        Visit visit = new Visit();
        visit.setId(id);
        visit.setTypWizyty(typWizyty);
        visit.setData(data);
        visit.setCena(cena);
        visit.setWeterynarzid(weterynarzid);
        visit.setZwierzeid(zwierzeid);
        visit.setZalecenieid(zalecenieid);
        return visit;
    }
}
