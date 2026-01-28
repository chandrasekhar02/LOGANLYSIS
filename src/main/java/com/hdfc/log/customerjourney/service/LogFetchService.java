package com.hdfc.log.customerjourney.service;

import com.hdfc.log.customerjourney.model.RawLogEvent;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class                                          LogFetchService {

    private final RestTemplate restTemplate;

    public LogFetchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RawLogEvent> fetchLogsByCustomerId(String customerId) {

        // ✅ CORRECT QUERY PARAM
        String url =
                "https://log-fetching-5.onrender.com/api/v1/logs?query=" + customerId;

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RawLogEvent>>() {}
            ).getBody();
        } catch (Exception ex) {
            System.err.println("Log fetch failed: " + ex.getMessage());
            return Collections.emptyList();
        }
    }
}
