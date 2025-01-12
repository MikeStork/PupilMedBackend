package com.pupilmed.pupilmedapi.service.factories;

import com.pupilmed.pupilmedapi.model.Recommendation;
import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.model.VisitWithRecommendation;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitWithRecommmendationFactoryTest {
    private RecommendationRepository recommendationRepository;
    private VisitWithRecommmendationFactory factory;

    @BeforeEach
    void setUp() {
        recommendationRepository = mock(RecommendationRepository.class);
        factory = new VisitWithRecommmendationFactory(recommendationRepository);
    }

    @Test
    void shouldCreateVisitWithRecommendation() {
        Visit visit = mock(Visit.class);
        Recommendation recommendation = mock(Recommendation.class);

        when(visit.getId()).thenReturn(43);
        when(visit.getTypWizyty()).thenReturn("Konsultacja");
        when(visit.getData()).thenReturn(LocalDate.of(2024, 1, 12));
        when(visit.getCena()).thenReturn(100);
        when(visit.getWeterynarzid()).thenReturn(23);
        when(visit.getGodzina()).thenReturn(LocalTime.of(10, 30));
        when(visit.getZalecenieid()).thenReturn(13);
        when(visit.getZwierzeid()).thenReturn(53);

        when(recommendationRepository.findById(13)).thenReturn(Optional.of(recommendation));
        when(recommendation.getDescription()).thenReturn("Podawać antybiotyk");

        VisitWithRecommendation visitWithRecommendation = factory.createVisit(visit);

        assertEquals(43, visitWithRecommendation.getId());
        assertEquals("Konsultacja", visitWithRecommendation.getTypWizyty());
        assertEquals(LocalDate.of(2024, 1, 12), visitWithRecommendation.getData());
        assertEquals(100, visitWithRecommendation.getCena());
        assertEquals(23, visitWithRecommendation.getWeterynarzid());
        assertEquals(LocalTime.of(10, 30), visitWithRecommendation.getGodzina());
        assertEquals(13, visitWithRecommendation.getZalecenieid());
        assertEquals(53, visitWithRecommendation.getZwierzeid());
        assertEquals("Podawać antybiotyk", visitWithRecommendation.getZalecenie());
    }

    @Test
    void shouldHandleMissingRecommendation() {
        Visit visit = mock(Visit.class);

        when(visit.getId()).thenReturn(43);
        when(visit.getTypWizyty()).thenReturn("Konsultacja");
        when(visit.getData()).thenReturn(LocalDate.of(2024, 1, 12));
        when(visit.getCena()).thenReturn(100);
        when(visit.getWeterynarzid()).thenReturn(23);
        when(visit.getGodzina()).thenReturn(LocalTime.of(10, 30));
        when(visit.getZalecenieid()).thenReturn(13);
        when(visit.getZwierzeid()).thenReturn(53);

        when(recommendationRepository.findById(13)).thenReturn(Optional.empty());

        VisitWithRecommendation visitWithRecommendation = factory.createVisit(visit);

        assertEquals(43, visitWithRecommendation.getId());
        assertEquals("Konsultacja", visitWithRecommendation.getTypWizyty());
        assertEquals(LocalDate.of(2024, 1, 12), visitWithRecommendation.getData());
        assertEquals(100, visitWithRecommendation.getCena());
        assertEquals(23, visitWithRecommendation.getWeterynarzid());
        assertEquals(LocalTime.of(10, 30), visitWithRecommendation.getGodzina());
        assertEquals(13, visitWithRecommendation.getZalecenieid());
        assertEquals(53, visitWithRecommendation.getZwierzeid());
        assertNull(visitWithRecommendation.getZalecenie(), "Zalecenie powinno być null jeśli nie znaleziono rekomendacji");
    }
}
