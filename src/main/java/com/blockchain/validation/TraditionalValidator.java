package com.blockchain.validation;

import com.blockchain.model.AccountState;
import com.blockchain.model.Block;
import com.blockchain.model.Transaction;

public class TraditionalValidator {

    public long validate(Block block, AccountState state) {

        long start = System.nanoTime();

        for (Transaction tx : block.getTransactions()) {

            // 🔐 Simulate digital signature verification cost
            tx.simulateSignatureVerification();

            // 💰 Update balances
            state.transfer(tx.getFrom(), tx.getTo(), tx.getAmount());
        }

        long end = System.nanoTime();

        return (end - start) / 1_000_000; // convert nanoseconds to milliseconds
    }
}