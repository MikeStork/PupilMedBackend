package com.pupilmed.pupilmedapi.service.facades;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import com.pupilmed.pupilmedapi.repository.VisitRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewInfoRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewRepository;
import com.pupilmed.pupilmedapi.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitServiceFacadeTest {

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private VisitViewRepository visitViewRepository;

    @Mock
    private VisitViewInfoRepository visitViewInfoRepository;

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private VisitService visitService; // Rzeczywista implementacja fasady

    private Visit visit;
    private VisitView visitView;
    private VisitInfoView visitInfoView;

    @BeforeEach
    void setUp() {
        visit = new Visit();
        visit.setId(1);
        visit.setCena(100);
        visit.setData(LocalDate.now());
        visit.setGodzina(LocalTime.of(10,0));
        visit.setWeterynarzid(2);
        visit.setZwierzeid(3);

        visitView = new VisitView();
        visitInfoView = new VisitInfoView();
    }

    @Test
    void testFindById_Success() {
        when(visitRepository.findById(1)).thenReturn(Optional.of(visit));

        Visit result = visitService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(visitRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(visitRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                visitService.findById(99)
        );

        assertEquals("VISIT/GET: Visit not found with id 99", exception.getMessage());
        verify(visitRepository, times(1)).findById(99);
    }

    @Test
    void testFindAllVisitViewsBetweenDates_Success() {
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 12, 31);

        when(visitViewRepository.findAllByDataBetween(from, to))
                .thenReturn(List.of(visitView));

        List<VisitView> result = visitService.findAllVisitViewsBetweenDates(from, to);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(visitViewRepository, times(1)).findAllByDataBetween(from, to);
    }

    @Test
    void testFindAllVisitsByUseridBetweenDates_Success() {
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 12, 31);

        when(visitViewInfoRepository.findAllByWlascicielIdAndDataBetween(1, from, to))
                .thenReturn(List.of(visitInfoView));

        List<VisitInfoView> result = visitService.findAllVisitsByUseridBetweenDates(1, from, to);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(visitViewInfoRepository, times(1))
                .findAllByWlascicielIdAndDataBetween(1, from, to);
    }
}
