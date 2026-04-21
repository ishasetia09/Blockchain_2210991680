package com.blockchain.benchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public void export(List<Metrics> metricsList, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Transactions,TraditionalTime,ParallelTime,TraditionalTPS,ParallelTPS,Speedup,MemoryBytes\n");

            for (Metrics m : metricsList) {
                writer.append(String.format("%d,%d,%d,%.2f,%.2f,%.2f,%d\n",
                        m.getTransactions(),
                        m.getTraditionalTime(),
                        m.getParallelTime(),
                        m.getTraditionalTPS(),
                        m.getParallelTPS(),
                        m.getSpeedup(),
                        m.getMemoryUsageBytes()
                ));
            }

            System.out.println("CSV Exported to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}