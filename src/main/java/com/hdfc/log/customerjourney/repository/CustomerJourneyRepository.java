package com.hdfc.log.customerjourney.repository;

import com.hdfc.log.customerjourney.model.CustomerJourneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJourneyRepository
        extends JpaRepository<CustomerJourneyEntity, Long> {

    Optional<CustomerJourneyEntity>
    findTopByCustomerIdAndCorrelationIdOrderByJourneyCompletedAtDesc(
            String customerId,
            String correlationId
    );
}
