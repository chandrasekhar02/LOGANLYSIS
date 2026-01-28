package com.hdfc.log.customerjourney.controller;

import com.hdfc.log.customerjourney.dto.JourneySearchRequest;
import com.hdfc.log.customerjourney.dto.JourneySearchResponse;
import com.hdfc.log.customerjourney.service.JourneySearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class JourneySearchController {

    private final JourneySearchService service;

    public JourneySearchController(JourneySearchService service) {
        this.service = service;
    }

    @PostMapping("/journeySearch")
    public JourneySearchResponse search(
            @RequestBody JourneySearchRequest request) {
        return service.search(request.getUnique_id());
    }
}
