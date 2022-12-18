package me.zeroest.systemmetrics.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.lang.management.MemoryUsage;

@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Memory {

    public Memory(MemoryUsage heap, MemoryUsage nonHeap) {
        heapInit = heap.getInit(); // 초기 상태 메모리
        heapUsed = heap.getUsed(); // 사용 중인 메모리
        heapCommitted = heap.getCommitted(); // JVM에 할당된 메모리
        heapMax = heap.getMax(); // 총 메모리 양

        nonHeapUsed = nonHeap.getUsed();
    }

    long heapInit;
    long heapUsed;
    long heapCommitted;
    long heapMax;

    long nonHeapUsed;

}
