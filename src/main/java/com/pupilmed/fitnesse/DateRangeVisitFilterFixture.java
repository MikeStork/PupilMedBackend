package com.pupilmed.fitnesse;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.service.filterStrategy.DateRangeVisitFilterStrategy;
import fit.RowFixture;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateRangeVisitFilterFixture extends RowFixture {
    private List<Visit> visits;

    public DateRangeVisitFilterFixture() {
        this.visits = new ArrayList<>();
        visits.add(createVisit(1, "Konsultacja", LocalDate.of(2025, 1, 1), 100, 1, 1, null));
        visits.add(createVisit(2, "Kontrola", LocalDate.of(2025, 1, 10), 200, 1, 2, null));
        visits.add(createVisit(3, "Operacja", LocalDate.of(2025, 2, 1), 300, 2, 3, 1));
    }

    @Override
    public Object[] query() throws Exception {
        List<Object> params = Arrays.asList(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31));
        DateRangeVisitFilterStrategy strategy = new DateRangeVisitFilterStrategy();
        List<Visit> filteredVisits = strategy.filterVisits(visits, params);
        return filteredVisits.toArray();
    }

    @Override
    public Class<?> getTargetClass() {
        return Visit.class;
    }

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
