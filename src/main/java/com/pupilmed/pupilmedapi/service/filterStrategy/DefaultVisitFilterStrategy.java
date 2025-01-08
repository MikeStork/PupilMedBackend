package com.pupilmed.pupilmedapi.service.filterStrategy;
import com.pupilmed.pupilmedapi.model.Visit;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DefaultVisitFilterStrategy implements VisitFilterStrategy {
    @Override
    public List<Visit> filterVisits(List<Visit> visits, List<Object> params) {
        return visits;
    }
}
