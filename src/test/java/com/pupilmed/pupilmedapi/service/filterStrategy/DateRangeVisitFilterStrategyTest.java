package com.pupilmed.pupilmedapi.service.filterStrategy;

import com.pupilmed.pupilmedapi.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DateRangeVisitFilterStrategyTest {
    private DateRangeVisitFilterStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DateRangeVisitFilterStrategy();
    }

    @Test
    void shouldFilterVisitsWithinDateRange() {
        Visit visit1 = mock(Visit.class);
        Visit visit2 = mock(Visit.class);
        Visit visit3 = mock(Visit.class);

        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 12, 31);

        when(visit1.getData()).thenReturn(LocalDate.of(2024, 2, 15));
        when(visit2.getData()).thenReturn(LocalDate.of(2024, 6, 10));
        when(visit3.getData()).thenReturn(LocalDate.of(2023, 12, 31));

        List<Visit> visits = Arrays.asList(visit1, visit2, visit3);
        List<Visit> filteredVisits = strategy.filterVisits(visits, Arrays.asList(from, to));

        assertEquals(2, filteredVisits.size());
        assertTrue(filteredVisits.contains(visit1));
        assertTrue(filteredVisits.contains(visit2));
        assertFalse(filteredVisits.contains(visit3));
    }

    @Test
    void shouldReturnEmptyListWhenNoVisitsMatch() {
        Visit visit1 = mock(Visit.class);
        Visit visit2 = mock(Visit.class);

        LocalDate from = LocalDate.of(2025, 1, 1);
        LocalDate to = LocalDate.of(2025, 12, 31);

        when(visit1.getData()).thenReturn(LocalDate.of(2024, 2, 15));
        when(visit2.getData()).thenReturn(LocalDate.of(2024, 6, 10));

        List<Visit> visits = Arrays.asList(visit1, visit2);
        List<Visit> filteredVisits = strategy.filterVisits(visits, Arrays.asList(from, to));

        assertTrue(filteredVisits.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenInvalidParametersProvided() {
        List<Visit> visits = Collections.emptyList();

        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                strategy.filterVisits(visits, Collections.singletonList(LocalDate.of(2024, 1, 1))));
        assertEquals("DateRangeVisitFilterStrategy requires exactly two LocalDate parameters.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                strategy.filterVisits(visits, Arrays.asList(LocalDate.of(2024, 1, 1), "not a date")));
        assertEquals("DateRangeVisitFilterStrategy requires both parameters to be instances of LocalDate.", exception2.getMessage());
    }
}
