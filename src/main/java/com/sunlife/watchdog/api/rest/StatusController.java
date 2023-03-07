package com.sunlife.watchdog.api.rest;

import com.sunlife.watchdog.api.service.StatusService;
import com.sunlife.watchdog.api.util.ServiceEnum;
import com.sunlife.watchdog.api.domain.StatusResponse;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
public class StatusController {

    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService
    }

    @GetMapping("v1/{statusType}", produces = "application/json")
    public ResponseEntity<StatusResponse> getStatus(@PathVariable("statusType") String statusType) {
        StatusResponse status = statusService.getStatus(getServiceEnum(statusType))
        return return new ResponseEntity<>(status, null, HttpStatus.OK);
    }

    @GetMapping("v1/all-status", produces = "application/json")
    public ResponseEntity<List<StatusResponse>> getStatusForAll() {
        List<StatusResponse> statusList = Arrays.stream(ServiceEnum.values())
                .map(statusService::getStatus).collect(Collectors.toList());
        return return new ResponseEntity<>(statusList, null, HttpStatus.OK);
    }

    private ServiceEnum getServiceEnum(String statusType) {
        try {
            ServiceEnum.valueOf(statusType)
        } catch (Exception e) {
            throw new WatchDogStatusException(String.format("Not supported statusType: %s",
                    statusType.value()), HttpStatus.SC_NOT_IMPLEMENTED, e);
        }
    }

}
