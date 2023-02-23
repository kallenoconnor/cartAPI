package com.techelevator.Services;

import com.techelevator.model.TaxDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaxAPI {
    public static String API_BASE_URL = "https://teapi.netlify.app/api/statetax?state=";
    private RestTemplate restTemplate = new RestTemplate();

    public TaxDto getTaxRate(String StateCode) {
        return restTemplate.getForObject(API_BASE_URL + StateCode, TaxDto.class);
    }
}
