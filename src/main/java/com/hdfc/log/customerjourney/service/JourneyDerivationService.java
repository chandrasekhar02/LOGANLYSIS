package com.hdfc.log.customerjourney.service;

import com.hdfc.log.customerjourney.enums.ErrorCode;
import com.hdfc.log.customerjourney.enums.JourneyStatus;
import com.hdfc.log.customerjourney.model.CustomerJourney;
import com.hdfc.log.customerjourney.model.JourneyStep;
import com.hdfc.log.customerjourney.model.RawLogEvent;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JourneyDerivationService {

    public CustomerJourney deriveJourney(List<RawLogEvent> logs) {

        if (logs == null || logs.isEmpty()) {
            throw new IllegalArgumentException("No logs found");
        }

        logs.sort(Comparator.comparing(RawLogEvent::getTransactionDate));

        CustomerJourney journey = new CustomerJourney();

        journey.setCustomerId(logs.get(0).getCustomerId());
        journey.setCorrelationId(logs.get(0).getCorrelationId());
        journey.setJourneyStartedAt(logs.get(0).getTransactionDate());
        journey.setJourneyCompletedAt(logs.get(logs.size() - 1).getTransactionDate());

        Map<String, String> stepStatusMap = new LinkedHashMap<>();

        for (RawLogEvent log : logs) {

            String step = log.getTransactionStep()
                    .toUpperCase()
                    .replace(" ", "_");

            String status = log.getApplicationSuccessType()
                    .equalsIgnoreCase("success")
                    ? "SUCCESS"
                    : "FAILED";

            stepStatusMap.put(step, status);


            if ("FAILED".equals(status) && journey.getFailedTransactionId() == null) {
                journey.setFailedTransactionId(log.getTransactionId());
                journey.setErrorCode(log.getErrorCodes());
            }
        }

        List<JourneyStep> steps = new ArrayList<>();
        for (Map.Entry<String, String> e : stepStatusMap.entrySet()) {
            steps.add(new JourneyStep(e.getKey(), e.getValue()));
        }
        journey.setSteps(steps);

        JourneyStatus finalStatus =
                stepStatusMap.containsValue("FAILED")
                        ? JourneyStatus.FAILED
                        : JourneyStatus.SUCCESS;

        journey.setFinalJourneyStatus(finalStatus);

        String currentStep = steps.get(steps.size() - 1).getStep();
        journey.setCurrentStep(currentStep);

        if (finalStatus == JourneyStatus.FAILED) {
            journey.setDropOffStep(currentStep);


            ErrorCode resolvedError =
                    ErrorReasonResolver.resolve(
                            journey.getDropOffStep(),
                            finalStatus.name()
                    );

            journey.setErrorCode(resolvedError.name());
        }

        return journey;
    }
}
