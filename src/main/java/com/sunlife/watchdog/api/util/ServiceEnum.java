package com.sunlife.watchdog.api.util;


public enum ServiceEnum {

    GOOGLE("google-status", "https://www.google.com/"),
    AMAZON("amazon-status", "https://www.amazon.com/");

    private final String serviceName;
    private final String serviceUrl;

    ServiceEnum(String serviceName, String serviceUrl) {
        this.serviceName = serviceName;
        this.serviceUrl = serviceUrl;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }
}