package com.hdfc.log.customerjourney.dto;

import lombok.Data;

@Data
public class JourneySearchRequest {
    private String unique_id;
    private String duration; // optional
}
