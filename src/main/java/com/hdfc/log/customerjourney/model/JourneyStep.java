package com.hdfc.log.customerjourney.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JourneyStep {
    private String step;
    private String status;
}
