package me.zeroest.systemmetrics.service;

import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@Service
public class CpuUsageService {

    public double getCpuUsage() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        return (osBean.getSystemLoadAverage() / availableProcessors) * 100;
    }

}
