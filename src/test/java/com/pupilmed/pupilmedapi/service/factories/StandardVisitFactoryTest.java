package com.pupilmed.pupilmedapi.service.factories;

import com.pupilmed.pupilmedapi.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StandardVisitFactoryTest {
    private StandardVisitFactory factory;

    @BeforeEach
    void setUp() {
        factory = new StandardVisitFactory();
    }

    @Test
    void shouldReturnSameVisitObject() {
        Visit visit = mock(Visit.class);
        Visit result = factory.createVisit(visit);

        assertSame(visit, result);
    }

    @Test
    void shouldReturnNullWhenNullIsPassed() {
        Visit result = factory.createVisit(null);

        assertNull(result, "Factory should return null when null is passed.");
    }

    @Test
    void shouldReturnSameReferenceEvenAfterModification() {
        Visit visit = new Visit();
        visit.setCena(100);

        Visit result = factory.createVisit(visit);

        assertSame(visit, result);

        // Modify visit and check if result is also modified
        visit.setCena(200);
        assertEquals(200, result.getCena(), "Both references should point to the same object.");
    }

    @Test
    void shouldHandleDifferentVisitInstances() {
        Visit visit1 = new Visit();
        visit1.setId(1);
        visit1.setCena(50);

        Visit visit2 = new Visit();
        visit2.setId(2);
        visit2.setCena(150);

        Visit result1 = factory.createVisit(visit1);
        Visit result2 = factory.createVisit(visit2);

        assertSame(visit1, result1);
        assertSame(visit2, result2);
        assertNotSame(visit1, visit2);
    }
}
