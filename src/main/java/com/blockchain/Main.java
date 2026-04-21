package com.blockchain;

import java.util.List;

import com.blockchain.benchmark.BenchmarkRunner;
import com.blockchain.benchmark.CSVExporter;
import com.blockchain.benchmark.Metrics;
import com.blockchain.visualization.GraphGenerator;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] testSizes = {100, 200, 500, 700, 1000};
        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU Threads: " + threads);

        BenchmarkRunner runner = new BenchmarkRunner();
        List<Metrics> results = runner.runBenchmarks(testSizes, threads);

        // CSV Export
        CSVExporter exporter = new CSVExporter();
        exporter.export(results, "results.csv");

        // Graph Generation
        GraphGenerator graphGen = new GraphGenerator();
        graphGen.generateGraphs(results);

        System.out.println("Benchmark Completed, CSV Exported, Graphs Generated.");
    }
}