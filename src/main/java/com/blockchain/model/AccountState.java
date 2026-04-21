package com.blockchain.model;

import java.util.concurrent.ConcurrentHashMap;

public class AccountState {

    private final ConcurrentHashMap<String, Double> balances = new ConcurrentHashMap<>();

    public void initializeAccount(String account, double balance) {
        balances.put(account, balance);
    }

    public boolean transfer(String from, String to, double amount) {

        balances.putIfAbsent(from, 0.0);
        balances.putIfAbsent(to, 0.0);

        synchronized (this) {
            if (balances.get(from) >= amount) {
                balances.put(from, balances.get(from) - amount);
                balances.put(to, balances.get(to) + amount);
                return true;
            }
        }
        return false;
    }

    public int getTotalAccounts() {
        return balances.size();
    }
}