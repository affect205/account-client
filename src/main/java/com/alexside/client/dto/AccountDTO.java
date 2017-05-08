package com.alexside.client.dto;

/**
 * Created by Alex on 07.05.2017.
 */
public class AccountDTO {
    private long id;
    private String number;
    private double balance;
    public AccountDTO() {}
    public AccountDTO(long id, String number, double balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
