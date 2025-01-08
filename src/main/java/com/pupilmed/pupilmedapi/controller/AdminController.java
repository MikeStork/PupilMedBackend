package com.pupilmed.pupilmedapi.controller;

import com.pupilmed.pupilmedapi.model.*;
import com.pupilmed.pupilmedapi.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/mainView/{from}/{to}")
    public List<VisitView> getVisitsForVetsBetweenDates(@PathVariable LocalDate from, @PathVariable LocalDate to) {
        return adminService.findAllVisitsBetweenDates(from, to);
    }

    @GetMapping("/visitInfoView/{id}")
    public VisitInfoView getVisitInfo(@PathVariable Integer id) {
        return adminService.findVisitInfoById(id);
    }

    @GetMapping("/petInfoView/{id}")
    public PetInfoView getPetInfo(@PathVariable Integer id) {
        return adminService.findPetInfoById(id);
    }
    @GetMapping("/usersInfoView")
    public List<UserInfoView> getUsersInfo() {
        return adminService.findAllUserInfoView();
    }

}
