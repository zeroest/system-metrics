package me.zeroest.systemmetrics.service;

import me.zeroest.systemmetrics.dto.FreeDiskSpace;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DiskServiceTest {

    private DiskService diskService = new DiskService();

    @Test
    void getFreeDiskSpace() {

        final String ROOT_PATH = "/";

        FreeDiskSpace freeDiskSpace = diskService.getFreeDiskSpace(ROOT_PATH);

        File root = new File(ROOT_PATH);

        long expectTotalSpace = root.getTotalSpace();
        long expectUsableSpace = root.getUsableSpace();

        assertEquals(expectTotalSpace, freeDiskSpace.getTotalSpace());
        assertEquals(expectUsableSpace, freeDiskSpace.getUsableSpace());

    }

}