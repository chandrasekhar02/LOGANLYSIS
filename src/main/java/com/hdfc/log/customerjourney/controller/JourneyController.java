package com.hdfc.log.customerjourney.controller;

import com.hdfc.log.customerjourney.model.CustomerJourney;
import com.hdfc.log.customerjourney.service.JourneyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    private final JourneyService service;

    public JourneyController(JourneyService service) {
        this.service = service;
    }

    @GetMapping("/process/{customerId}")
    public CustomerJourney process(@PathVariable String customerId) {
        return service.processCustomerJourney(customerId.trim());
    }


}
