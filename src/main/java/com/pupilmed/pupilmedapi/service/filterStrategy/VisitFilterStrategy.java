package com.pupilmed.pupilmedapi.service.filterStrategy;

import com.pupilmed.pupilmedapi.model.Visit;

import java.util.List;

public interface VisitFilterStrategy {
    List<Visit> filterVisits(List<Visit> visits, List<Object> params);
}

