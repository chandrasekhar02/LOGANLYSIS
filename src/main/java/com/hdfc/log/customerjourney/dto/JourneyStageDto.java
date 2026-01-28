package com.hdfc.log.customerjourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JourneyStageDto {


    private String transaction_id;

    private String state;
    private LocalDateTime timestamp;
    private String error_code;
}
