package com.lxisoft.crimestopper.client.red_alert.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.lxisoft.crimestopper.client.red_alert.ClientConfiguration;

@FeignClient(name="${RedAlert.name:RedAlert}", url="${RedAlert.url:localhost:8082/RedAlert}", configuration = ClientConfiguration.class)
public interface ReportResourceApiClient extends ReportResourceApi {
}