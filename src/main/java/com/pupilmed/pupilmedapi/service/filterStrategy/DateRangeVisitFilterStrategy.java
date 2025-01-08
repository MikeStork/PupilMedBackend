package com.pupilmed.pupilmedapi.service.filterStrategy;

import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DateRangeVisitFilterStrategy implements VisitFilterStrategy {
    @Override
    public List<Visit> filterVisits(List<Visit> visits, List<Object> params) {
        if (params.size() < 2 || !(params.get(0) instanceof LocalDate) || !(params.get(1) instanceof LocalDate)) {
            throw new IllegalArgumentException("DateRangeVisitFilterStrategy requires two LocalDate parameters.");
        }

        LocalDate from = (LocalDate) params.get(0);
        LocalDate to = (LocalDate) params.get(1);

        return visits.stream()
                .filter(visit -> visit.getData().isAfter(from) &&
                        visit.getData().isBefore(to))
                .collect(Collectors.toList());
    }
}
