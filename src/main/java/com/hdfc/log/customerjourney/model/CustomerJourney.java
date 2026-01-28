package com.hdfc.log.customerjourney.model;

import com.hdfc.log.customerjourney.enums.JourneyStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerJourney {

    private String customerId;
    private String correlationId;


    private JourneyStatus finalJourneyStatus;

    private String currentStep;
    private String dropOffStep;


    private String failedTransactionId;
    private String errorCode;

    private LocalDateTime journeyStartedAt;
    private LocalDateTime journeyCompletedAt;

    private List<JourneyStep> steps;
}
