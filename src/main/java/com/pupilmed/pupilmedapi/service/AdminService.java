package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.*;
import com.pupilmed.pupilmedapi.repository.*;
import com.pupilmed.pupilmedapi.service.facades.AdminServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceFacade {
    @Autowired
    VisitViewRepository visitViewRepository;

    @Autowired
    VisitViewInfoRepository visitViewInfoRepository;
    @Autowired
    private PetInfoViewRepository petInfoViewRepository;
    @Autowired
    private UserInfoViewRepository userInfoViewRepository;

    public List<VisitView> findAllVisitsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return visitViewRepository.findAllByDataBetween(startDate, endDate);
    }

    public VisitInfoView findVisitInfoById(Integer id) {
        Optional<VisitInfoView> adminVisitInfoView = visitViewInfoRepository.findById(id);
        if(adminVisitInfoView.isEmpty()) {
            throw new RuntimeException("Visit info not found");
        }
        return adminVisitInfoView.get();
    }

    public PetInfoView findPetInfoById(Integer id) {
        Optional<PetInfoView> adminPetInfoView = petInfoViewRepository.findById(id);
        if(adminPetInfoView.isEmpty()) {
            throw new RuntimeException("Pet info not found");
        }
        return adminPetInfoView.get();
    }

    public List<UserInfoView> findAllUserInfoView() {
        return userInfoViewRepository.findAll();
    }
}
