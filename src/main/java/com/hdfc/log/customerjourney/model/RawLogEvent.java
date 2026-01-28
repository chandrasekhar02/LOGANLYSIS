package com.hdfc.log.customerjourney.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RawLogEvent {

    private String customerId;
    private String correlationId;
    private String transactionId;
    private String transactionStep;
    private String applicationSuccessType;
    private String errorCodes;
    private LocalDateTime transactionDate;
}
