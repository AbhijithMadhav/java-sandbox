package com.am.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Print peak memory usage of the application
 */
public class MemoryPoolMXBeansExample {
    public static void main(String s[]) {

        Map<MemoryType, Long> beforePeakUsageMap = new HashMap<>();
        ManagementFactory.getMemoryPoolMXBeans().forEach(memoryPoolMXBean -> {
            beforePeakUsageMap.put(memoryPoolMXBean.getType(), beforePeakUsageMap.getOrDefault(memoryPoolMXBean.getType(), 0L) + memoryPoolMXBean.getPeakUsage().getUsed());
        });



        List<String> list = new LinkedList<>();
        IntStream.range(0, 1000000).forEach( i -> list.add(String.valueOf(i)));

        Map<MemoryType, Long> afterPeakUsageMap = new HashMap<>();
        ManagementFactory.getMemoryPoolMXBeans().forEach(memoryPoolMXBean -> {
            afterPeakUsageMap.put(memoryPoolMXBean.getType(), afterPeakUsageMap.getOrDefault(memoryPoolMXBean.getType(), 0L) + memoryPoolMXBean.getPeakUsage().getUsed());
        });

        beforePeakUsageMap.keySet().forEach(memoryType -> {
            Long before = beforePeakUsageMap.get(memoryType);
            Long after = afterPeakUsageMap.get(memoryType);
            System.out.println(memoryType + " : " + ((after - before) * 100/ before) + "% growth[" + before /1000 + "kb -> " + after /1000 + "kb]");
        });
    }


    private static void printUsage() {
        System.out.println("Usage");
        ManagementFactory.getMemoryPoolMXBeans().forEach(memoryPoolMXBean -> {
            System.out.println(memoryPoolMXBean.getName() + " : " + memoryPoolMXBean.getUsage().getUsed());
        });
        System.out.println("");
    }

    private static void printPeak() {
        System.out.println("Peak Usage");
        ManagementFactory.getMemoryPoolMXBeans().forEach(memoryPoolMXBean -> {
            System.out.println(memoryPoolMXBean.getName() + " : " + memoryPoolMXBean.getPeakUsage().getUsed());
        });
        System.out.println("");
    }
}
