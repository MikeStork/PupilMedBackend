package com.pupilmed.pupilmedapi.service.filterStrategy;

import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DateRangeVisitFilterStrategy implements VisitFilterStrategy {
    @Override
    public List<Visit> filterVisits(List<Visit> visits, List<Object> params) {
        // Walidacja parametrów
        validateParams(params);

        LocalDate from = (LocalDate) params.get(0);
        LocalDate to = (LocalDate) params.get(1);

        // Walidacja zakresu dat
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date (from) must not be after end date (to).");
        }

        // Filtrowanie wizyt w zakresie dat (łącznie z datami granicznymi)
        return visits.stream()
                .filter(visit -> isWithinRange(visit.getData(), from, to))
                .collect(Collectors.toList());
    }

    // Metoda sprawdzająca, czy data mieści się w zakresie
    private boolean isWithinRange(LocalDate visitDate, LocalDate from, LocalDate to) {
        return !visitDate.isBefore(from) && !visitDate.isAfter(to);
    }

    // Walidacja parametrów wejściowych
    private void validateParams(List<Object> params) {
        if (params == null || params.size() < 2) {
            throw new IllegalArgumentException("DateRangeVisitFilterStrategy requires exactly two LocalDate parameters.");
        }

        // Sprawdzanie, czy oba parametry są instancjami LocalDate
        if (!(params.get(0) instanceof LocalDate) || !(params.get(1) instanceof LocalDate)) {
            throw new IllegalArgumentException("DateRangeVisitFilterStrategy requires both parameters to be instances of LocalDate.");
        }

        // Sprawdzanie, czy parametry są niepuste
        if (Objects.isNull(params.get(0)) || Objects.isNull(params.get(1))) {
            throw new IllegalArgumentException("DateRangeVisitFilterStrategy parameters must not be null.");
        }
    }
}
