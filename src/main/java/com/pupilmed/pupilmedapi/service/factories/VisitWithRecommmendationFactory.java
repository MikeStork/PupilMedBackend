package com.pupilmed.pupilmedapi.service.factories;

import com.pupilmed.pupilmedapi.model.Recommendation;
import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.model.VisitWithRecommendation;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitWithRecommmendationFactory implements VisitFactory {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public VisitWithRecommmendationFactory(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public VisitWithRecommendation createVisit(Visit visit) {
        VisitWithRecommendation newVisit = new VisitWithRecommendation();
        newVisit.setId(visit.getId());
        newVisit.setTypWizyty(visit.getTypWizyty());
        newVisit.setData(visit.getData());
        newVisit.setCena(visit.getCena());
        newVisit.setWeterynarzid(visit.getWeterynarzid());
        newVisit.setGodzina(visit.getGodzina());
        newVisit.setZalecenieid(visit.getZalecenieid());
        newVisit.setZwierzeid(visit.getZwierzeid());

        // Obsługa przypadku, gdy rekomendacja nie istnieje
        recommendationRepository.findById(visit.getZalecenieid())
                .ifPresent(recommendation -> newVisit.setZalecenie(recommendation.getDescription()));

        return newVisit;
    }
}
