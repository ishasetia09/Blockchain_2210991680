package com.blockchain.model;

public class Transaction {

    private String from;
    private String to;
    private double amount;

    public Transaction(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    // 🔐 Simulate digital signature verification cost
    // make cpu busy 
    public void simulateSignatureVerification() {

        double result = 0;

    // 🔥 Increased workload
        for (int i = 0; i < 5_000_00; i++) {
        result += Math.sqrt(i) * Math.sin(i);
        }
    }
}