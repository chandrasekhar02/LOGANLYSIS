package com.hdfc.log.customerjourney.service;

import com.hdfc.log.customerjourney.dto.JourneySearchResponse;
import com.hdfc.log.customerjourney.dto.JourneyStageDto;
import com.hdfc.log.customerjourney.exception.JourneyNotFoundException;
import com.hdfc.log.customerjourney.model.CustomerJourneyEntity;
import com.hdfc.log.customerjourney.repository.CustomerJourneyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneySearchService {

    private final CustomerJourneyRepository repository;

    public JourneySearchService(CustomerJourneyRepository repository) {
        this.repository = repository;
    }

    public JourneySearchResponse search(String customerId) {

        CustomerJourneyEntity entity =
                repository.findTopByCustomerIdOrderByJourneyCompletedAtDesc(customerId)
                        .orElseThrow(() ->
                                new JourneyNotFoundException(
                                        "Journey not found for customerId=" + customerId
                                )
                        );

        JourneySearchResponse response = new JourneySearchResponse();
        response.setCustomer_id(entity.getCustomerId());
        response.setJourney_status(entity.getJourneyStatus().name());

        // Single failed stage (latest journey)
        JourneyStageDto stage = new JourneyStageDto(
                entity.getFailedTransactionId(),   // ✅ TRANSACTION ID (NOT correlation)
                entity.getCurrentStep(),
                entity.getJourneyCompletedAt(),
                entity.getErrorCode()
        );

        response.setStages(List.of(stage));

        return response;
    }
}
