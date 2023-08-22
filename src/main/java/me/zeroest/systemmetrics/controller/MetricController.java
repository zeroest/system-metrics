package me.zeroest.systemmetrics.controller;

import io.prometheus.client.Histogram;
import io.prometheus.client.Supplier;
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
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class MetricController {

    @Value("${root-path}")
    private String rootPath;

    private final DiskService diskService;
    private final CpuUsageService cpuUsageService;
    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<MetricResponse> getMetric() {

        FreeDiskSpace freeDiskSpace = diskService.getFreeDiskSpace(rootPath);
        double cpuUsage = cpuUsageService.getCpuUsage();
        Memory memory = memoryService.getMemory();

        return new ResponseEntity<>(
            new MetricResponse(freeDiskSpace, cpuUsage, memory)
            , HttpStatus.OK
        );

    }

    private static final Random random = new Random();
    private static final Supplier<Long> latency = () -> random.nextLong(2000);
    private final Histogram requestLatency = Histogram.build()
        .name("requests_latency_seconds")
        .help("Request latency in seconds.")
        .exponentialBuckets(0.01, 2, 16)
        .register();

    @GetMapping("/histogram")
    public Mono<String> histogram() {
        Histogram.Timer requestTimer = requestLatency.startTimer();
        try {
            Thread.sleep(latency.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            requestTimer.observeDuration();
        }

        return Mono.just("OK");
    }

}
