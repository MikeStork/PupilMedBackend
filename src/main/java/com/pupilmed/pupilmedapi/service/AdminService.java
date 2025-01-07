package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.*;
import com.pupilmed.pupilmedapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminVisitViewRepository adminVisitViewRepository;

    @Autowired
    AdminVisitViewInfoRepository adminVisitViewInfoRepository;
    @Autowired
    private AdminPetInfoViewRepository adminPetInfoViewRepository;
    @Autowired
    private AdminUserInfoViewRepository adminUserInfoViewRepository;

    public List<AdminVisitView> findAllVisitsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return adminVisitViewRepository.findAllByDataBetween(startDate, endDate);
    }

    public AdminVisitInfoView findVisitInfoById(Integer id) {
        Optional<AdminVisitInfoView> adminVisitInfoView = adminVisitViewInfoRepository.findById(id);
        if(adminVisitInfoView.isEmpty()) {
            throw new RuntimeException("Visit info not found");
        }
        return adminVisitInfoView.get();
    }

    public AdminPetInfoView findPetInfoById(Integer id) {
        Optional<AdminPetInfoView> adminPetInfoView = adminPetInfoViewRepository.findById(id);
        if(adminPetInfoView.isEmpty()) {
            throw new RuntimeException("Pet info not found");
        }
        return adminPetInfoView.get();
    }

    public List<AdminUserInfoView> findAllUserInfoView() {
        return adminUserInfoViewRepository.findAll();
    }
}
