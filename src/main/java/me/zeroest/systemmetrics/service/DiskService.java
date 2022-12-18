package me.zeroest.systemmetrics.service;

import me.zeroest.systemmetrics.dto.FreeDiskSpace;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DiskService {

    public FreeDiskSpace getFreeDiskSpace(String rootPath) {
        File root = new File(rootPath);

        return new FreeDiskSpace(
                root.getTotalSpace(),
                root.getUsableSpace()
        );
    }

}
