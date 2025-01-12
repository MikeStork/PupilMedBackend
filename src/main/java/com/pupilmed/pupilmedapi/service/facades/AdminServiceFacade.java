package com.pupilmed.pupilmedapi.service.facades;
import com.pupilmed.pupilmedapi.model.PetInfoView;
import com.pupilmed.pupilmedapi.model.UserInfoView;
import com.pupilmed.pupilmedapi.model.VisitInfoView;
import com.pupilmed.pupilmedapi.model.VisitView;

import java.time.LocalDate;
import java.util.List;

public interface AdminServiceFacade  {
    List<VisitView> findAllVisitsBetweenDates(LocalDate startDate, LocalDate endDate);
    VisitInfoView findVisitInfoById(Integer id);
    PetInfoView findPetInfoById(Integer id);
    List<UserInfoView> findAllUserInfoView();
}


