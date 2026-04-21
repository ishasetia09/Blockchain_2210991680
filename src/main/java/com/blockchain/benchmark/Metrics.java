package com.blockchain.benchmark;

public class Metrics {

    private final int transactions;
    private final long traditionalTime;
    private final long parallelTime;
    private final double traditionalTPS;
    private final double parallelTPS;
    private final double speedup;
    private final long memoryUsageBytes;

    public Metrics(int transactions, long traditionalTime, long parallelTime, long memoryUsageBytes) {
        this.transactions = transactions;
        this.traditionalTime = traditionalTime;
        this.parallelTime = parallelTime;
        this.memoryUsageBytes = memoryUsageBytes;

        this.traditionalTPS = transactions / (traditionalTime / 1000.0);
        this.parallelTPS = transactions / (parallelTime / 1000.0);
        this.speedup = (double) traditionalTime / parallelTime;
    }

    public int getTransactions() { return transactions; }
    public long getTraditionalTime() { return traditionalTime; }
    public long getParallelTime() { return parallelTime; }
    public double getTraditionalTPS() { return traditionalTPS; }
    public double getParallelTPS() { return parallelTPS; }
    public double getSpeedup() { return speedup; }
    public long getMemoryUsageBytes() { return memoryUsageBytes; }
}