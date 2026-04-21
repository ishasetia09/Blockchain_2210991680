package com.blockchain.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.blockchain.model.AccountState;
import com.blockchain.model.Block;
import com.blockchain.model.Transaction;
import com.blockchain.validation.ParallelValidator;
import com.blockchain.validation.TraditionalValidator;

public class BenchmarkRunner {

    public List<Metrics> runBenchmarks(int[] testSizes, int threads) throws InterruptedException {

        List<Metrics> results = new ArrayList<>();

        for (int size : testSizes) {
            System.out.println("Running test for transactions: " + size);

            List<Transaction> transactions = generateTransactions(size);
            Block block = new Block(transactions);

            AccountState state1 = new AccountState();
            AccountState state2 = new AccountState();
            initializeAccounts(state1);
            initializeAccounts(state2);

            TraditionalValidator traditional = new TraditionalValidator();
            ParallelValidator parallel = new ParallelValidator(threads);

            // Memory before
            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // Suggest GC
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

            long traditionalTime = traditional.validate(block, state1);
            long parallelTime = parallel.validate(block, state2);

            runtime.gc();
            long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
            long memoryUsed = memoryAfter - memoryBefore;

            Metrics metrics = new Metrics(size, traditionalTime, parallelTime, memoryUsed);
            results.add(metrics);

            System.out.printf("Traditional: %d ms, Parallel: %d ms, Memory: %.2f MB\n",
                    traditionalTime, parallelTime, memoryUsed / (1024.0*1024.0));
            System.out.println("-----------------------------------");
        }

        return results;
    }

    private List<Transaction> generateTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String from = "A" + ThreadLocalRandom.current().nextInt(1, 100);
            String to = "A" + ThreadLocalRandom.current().nextInt(1, 100);
            double amount = ThreadLocalRandom.current().nextDouble(1, 100);
            transactions.add(new Transaction(from, to, amount));
        }
        return transactions;
    }

    private void initializeAccounts(AccountState state) {
        for (int i = 1; i <= 100; i++) {
            state.initializeAccount("A" + i, 10000);
        }
    }
}