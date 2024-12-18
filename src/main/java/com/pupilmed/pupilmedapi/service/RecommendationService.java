package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.Recommendation;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import com.pupilmed.pupilmedapi.repository.RecommendationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Transactional
    public Recommendation save(Recommendation recommendation){
        return recommendationRepository.saveAndFlush(recommendation);
    }
    public Recommendation findById(int id){
        Optional<Recommendation> recommendation = recommendationRepository.findById(id);
        if(recommendation.isEmpty()) {
            throw new RuntimeException("RECOMMENDATION/GET: Recommendation not found");
        }else{
            return recommendation.get();
        }
    }
    public List<Recommendation> findAll(){
        return recommendationRepository.findAll();
    }

    @Transactional
    public Recommendation update(Recommendation recommendation){
        Optional<Recommendation> found_recommendation = recommendationRepository.findById(recommendation.getId());
        if(found_recommendation.isEmpty()){
            throw new RuntimeException("RECOMMENDATION/UPDATE: Recommendation not found");
        }
        Recommendation recommendationToUpdate = found_recommendation.get();
        recommendationToUpdate.setDescription(recommendation.getDescription());

        return recommendationRepository.save(recommendationToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<Recommendation> found_recommendation = recommendationRepository.findById(id);
        if(found_recommendation.isEmpty()){
            throw new RuntimeException("RECOMMENDATION/DELETE: Recommendation not found");
        }
        System.out.println("Attempting to delete recommendation with id: " + id);  // Logowanie przed usunięciem
        recommendationRepository.deleteById(id);  // Usuwanie zalecenia
        System.out.println("Recommendation with id: " + id + " has been deleted");  // Logowanie po usunięciu
        recommendationRepository.flush();

    }
}
