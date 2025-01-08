package com.pupilmed.pupilmedapi.service.factories;

import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.stereotype.Component;

@Component
public class StandardVisitFactory implements VisitFactory {

    @Override
    public Visit createVisit(Visit visit) {
        return visit;
    }
}
