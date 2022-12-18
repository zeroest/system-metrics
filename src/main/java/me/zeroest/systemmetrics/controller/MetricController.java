package me.zeroest.systemmetrics.controller;

import lombok.RequiredArgsConstructor;
import me.zeroest.systemmetrics.dto.FreeDiskSpace;
import me.zeroest.systemmetrics.dto.Memory;
import me.zeroest.systemmetrics.dto.MetricResponse;
import me.zeroest.systemmetrics.service.CpuUsageService;
import me.zeroest.systemmetrics.service.DiskService;
import me.zeroest.systemmetrics.service.MemoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricController {

    @Value("${root-path}")
    private String ROOT_PATH;

    private final DiskService diskService;
    private final CpuUsageService cpuUsageService;
    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<MetricResponse> getMetric() {

        FreeDiskSpace freeDiskSpace = diskService.getFreeDiskSpace(ROOT_PATH);
        double cpuUsage = cpuUsageService.getCpuUsage();
        Memory memory = memoryService.getMemory();

        return new ResponseEntity(
                new MetricResponse(freeDiskSpace, cpuUsage, memory)
                , HttpStatus.OK
        );

    }

}
