package com.pupilmed.pupilmedapi.service.filterStrategy;

import com.pupilmed.pupilmedapi.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultVisitFilterStrategyTest {
    private DefaultVisitFilterStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DefaultVisitFilterStrategy();
    }

    @Test
    void shouldReturnSameListAsInput() {
        Visit visit1 = mock(Visit.class);
        Visit visit2 = mock(Visit.class);
        List<Visit> visits = Arrays.asList(visit1, visit2);

        List<Visit> result = strategy.filterVisits(visits, Collections.emptyList());

        assertEquals(visits, result);
    }

    @Test
    void shouldReturnEmptyListWhenInputIsEmpty() {
        List<Visit> visits = Collections.emptyList();
        List<Visit> result = strategy.filterVisits(visits, Collections.emptyList());

        assertTrue(result.isEmpty());
    }
}