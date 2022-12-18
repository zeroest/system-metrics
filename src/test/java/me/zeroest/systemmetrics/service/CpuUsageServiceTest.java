package me.zeroest.systemmetrics.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpuUsageServiceTest {
    
    CpuUsageService cpuUsageService = new CpuUsageService();

    @Test
    void getCpuUsage() {

        double cpuUsage = cpuUsageService.getCpuUsage();
        System.out.println("cpuUsage = " + cpuUsage);
        
    }
}