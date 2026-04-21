package com.blockchain.visualization;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.blockchain.benchmark.Metrics;

public class GraphGenerator {

    public void generateGraphs(List<Metrics> metricsList) {

        DefaultCategoryDataset timeDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset tpsDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset speedupDataset = new DefaultCategoryDataset();

        for (Metrics m : metricsList) {
            String tx = String.valueOf(m.getTransactions());
            timeDataset.addValue(m.getTraditionalTime(), "Traditional", tx);
            timeDataset.addValue(m.getParallelTime(), "Parallel", tx);

            tpsDataset.addValue(m.getTraditionalTPS(), "Traditional TPS", tx);
            tpsDataset.addValue(m.getParallelTPS(), "Parallel TPS", tx);

            speedupDataset.addValue(m.getSpeedup(), "Speedup", tx);
        }

        createChart(timeDataset, "Transactions vs Validation Time", "Transactions", "Time (ms)", "validation_time.png");
        createChart(tpsDataset, "Transactions vs Throughput", "Transactions", "TPS", "throughput.png");
        createChart(speedupDataset, "Transactions vs Speedup", "Transactions", "Speedup", "speedup.png");
    }

    private void createChart(DefaultCategoryDataset dataset, String title, String xLabel, String yLabel, String fileName) {
        JFreeChart lineChart = ChartFactory.createLineChart(title, xLabel, yLabel, dataset);
        try {
            ChartUtils.saveChartAsPNG(new File(fileName), lineChart, 800, 600);
            System.out.println("Graph saved as " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}