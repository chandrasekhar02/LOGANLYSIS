package com.hdfc.log.customerjourney.dto;

import lombok.Data;
import java.util.List;

@Data
public class JourneySearchResponse {

    private String customer_id;
    private String journey_status;
    private List<JourneyStageDto> stages;
}
