package com.hdfc.log.customerjourney.model;

import com.hdfc.log.customerjourney.enums.JourneyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class CustomerJourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String correlationId;

    @Enumerated(EnumType.STRING)
    private JourneyStatus journeyStatus;

    private String currentStep;
    private String dropOffStep;

    private String failedTransactionId;
    private String errorCode;

    private LocalDateTime journeyStartedAt;
    private LocalDateTime journeyCompletedAt;
}
