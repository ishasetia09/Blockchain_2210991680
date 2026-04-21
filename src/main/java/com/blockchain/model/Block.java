package com.blockchain.model;

import java.util.List;

public class Block {

    private final List<Transaction> transactions;

    public Block(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}