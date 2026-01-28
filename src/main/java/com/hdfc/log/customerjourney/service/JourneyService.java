package com.hdfc.log.customerjourney.service;

import com.hdfc.log.customerjourney.model.*;
import com.hdfc.log.customerjourney.repository.CustomerJourneyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyService {

    private final LogFetchService logFetchService;
    private final JourneyDerivationService derivationService;
    private final CustomerJourneyRepository repository;

    public JourneyService(
            LogFetchService logFetchService,
            JourneyDerivationService derivationService,
            CustomerJourneyRepository repository) {
        this.logFetchService = logFetchService;
        this.derivationService = derivationService;
        this.repository = repository;
    }

    public CustomerJourney processCustomerJourney(String customerId) {

        // 1. Fetch logs
        List<RawLogEvent> logs =
                logFetchService.fetchLogsByCustomerId(customerId);

        // 2. Derive journey
        CustomerJourney journey =
                derivationService.deriveJourney(logs);

        // 3. Fetch latest journey for same customer + correlation
        CustomerJourneyEntity entity =
                repository
                        .findTopByCustomerIdAndCorrelationIdOrderByJourneyCompletedAtDesc(
                                journey.getCustomerId(),
                                journey.getCorrelationId()
                        )
                        .orElse(new CustomerJourneyEntity());


        entity.setCustomerId(journey.getCustomerId());
        entity.setCorrelationId(journey.getCorrelationId());
        entity.setJourneyStatus(journey.getFinalJourneyStatus());
        entity.setCurrentStep(journey.getCurrentStep());
        entity.setDropOffStep(journey.getDropOffStep());
        entity.setFailedTransactionId(journey.getFailedTransactionId());
        entity.setErrorCode(journey.getErrorCode());
        entity.setJourneyStartedAt(journey.getJourneyStartedAt());
        entity.setJourneyCompletedAt(journey.getJourneyCompletedAt());

        // 5. Save
        repository.save(entity);

        return journey;
    }
}
