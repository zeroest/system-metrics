package me.zeroest.systemmetrics.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetricResponse {

    FreeDiskSpace freeDiskSpace;
    double cpuUsage;
    Memory memory;

}
