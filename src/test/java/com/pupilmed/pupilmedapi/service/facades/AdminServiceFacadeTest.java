package com.pupilmed.pupilmedapi.service.facades;

import com.pupilmed.pupilmedapi.model.PetInfoView;
import com.pupilmed.pupilmedapi.model.UserInfoView;
import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;
import com.pupilmed.pupilmedapi.repository.PetInfoViewRepository;
import com.pupilmed.pupilmedapi.repository.UserInfoViewRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewInfoRepository;
import com.pupilmed.pupilmedapi.repository.VisitViewRepository;
import com.pupilmed.pupilmedapi.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceFacadeTest {

    @Mock
    private VisitViewRepository visitViewRepository;

    @Mock
    private VisitViewInfoRepository visitViewInfoRepository;

    @Mock
    private PetInfoViewRepository petInfoViewRepository;

    @Mock
    private UserInfoViewRepository userInfoViewRepository;

    @InjectMocks
    private AdminService adminService; // Testujemy implementację fasady

    private VisitView visitView;
    private VisitInfoView visitInfoView;
    private PetInfoView petInfoView;
    private UserInfoView userInfoView;

    @BeforeEach
    void setUp() {
        visitView = new VisitView();
        visitInfoView = new VisitInfoView();
        petInfoView = new PetInfoView();
        userInfoView = new UserInfoView();
    }

    @Test
    void testFindAllVisitsBetweenDates_Success() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        when(visitViewRepository.findAllByDataBetween(startDate, endDate))
                .thenReturn(List.of(visitView));

        List<VisitView> result = adminService.findAllVisitsBetweenDates(startDate, endDate);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(visitViewRepository, times(1)).findAllByDataBetween(startDate, endDate);
    }

    @Test
    void testFindVisitInfoById_Success() {
        Integer visitId = 1;

        when(visitViewInfoRepository.findById(visitId))
                .thenReturn(Optional.of(visitInfoView));

        VisitInfoView result = adminService.findVisitInfoById(visitId);

        assertNotNull(result);
        verify(visitViewInfoRepository, times(1)).findById(visitId);
    }

    @Test
    void testFindVisitInfoById_NotFound() {
        Integer visitId = 99;

        when(visitViewInfoRepository.findById(visitId))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                adminService.findVisitInfoById(visitId)
        );

        assertEquals("Visit info not found", exception.getMessage());
        verify(visitViewInfoRepository, times(1)).findById(visitId);
    }

    @Test
    void testFindPetInfoById_Success() {
        Integer petId = 1;

        when(petInfoViewRepository.findById(petId))
                .thenReturn(Optional.of(petInfoView));

        PetInfoView result = adminService.findPetInfoById(petId);

        assertNotNull(result);
        verify(petInfoViewRepository, times(1)).findById(petId);
    }

    @Test
    void testFindPetInfoById_NotFound() {
        Integer petId = 99;

        when(petInfoViewRepository.findById(petId))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                adminService.findPetInfoById(petId)
        );

        assertEquals("Pet info not found", exception.getMessage());
        verify(petInfoViewRepository, times(1)).findById(petId);
    }

    @Test
    void testFindAllUserInfoView_Success() {
        when(userInfoViewRepository.findAll())
                .thenReturn(List.of(userInfoView));

        List<UserInfoView> result = adminService.findAllUserInfoView();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userInfoViewRepository, times(1)).findAll();
    }

    @Test
    void testFindAllUserInfoView_EmptyList() {
        when(userInfoViewRepository.findAll())
                .thenReturn(List.of());

        List<UserInfoView> result = adminService.findAllUserInfoView();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userInfoViewRepository, times(1)).findAll();
    }
}
