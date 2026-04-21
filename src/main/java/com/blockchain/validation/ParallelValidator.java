package com.blockchain.validation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.blockchain.model.AccountState;
import com.blockchain.model.Block;
import com.blockchain.model.Transaction;

public class ParallelValidator {

    private final int threads;

    public ParallelValidator(int threads) {
        this.threads = threads;
    }

    public long validate(Block block, AccountState state) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        long start = System.nanoTime(); // START MEASURING TIME

        // 🔐 Step 1: Parallel signature verification
        // simulateSignatureVerification = IT IS DIFINE IN THE MODEL TRANSACTION
        // simulateSignatureVerification = TAKING CPU TIME 
        for (Transaction tx : block.getTransactions()) {
            executor.submit(tx::simulateSignatureVerification);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        // 💰 Step 2: Sequential state update (avoids lock contention)
        for (Transaction tx : block.getTransactions()) {
            state.transfer(tx.getFrom(), tx.getTo(), tx.getAmount());
        }

        long end = System.nanoTime(); // STOP MEASURING TIME

        return (end - start) / 1_000_000; // convert ns to ms
    }
}