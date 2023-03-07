package com.sunlife.watchdog.service;

import com.sunlife.watchdog.api.util.ServiceEnum;
import com.sunlife.watchdog.api.domain.StatusResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMethod;

public class StatusService {

    public StatusResponse getStatus(ServiceEnum statusType) {
        try {
            String url = statusType.getServiceUrl();
            LocalDateTime start = LocalDateTime.now();
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(RequestMethod.GET.name());
            int statusCoce = con.getResponseCode();
            long duration = ChronoUnit.MILLIS.between(start, LocalDateTime.now());

            return StatusResponse.builder()
                    .url(url)
                    .statusCode(statusCoce)
                    .duration(duration)
                    .date(start)
                    .build();

        } catch(IOException e) {
            throw new WatchDogStatusException(String.format("Error processing statusType: %s",
                    statusType.name()), HttpStatus.SC_INTERNAL_SERVER_ERROR, e);
        }
    }
}
