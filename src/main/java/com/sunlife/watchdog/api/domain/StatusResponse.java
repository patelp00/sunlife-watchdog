package com.sunlife.watchdog.api.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponse {
    String url;
    int statusCode;
    long duration;
    LocalDateTime date;

}