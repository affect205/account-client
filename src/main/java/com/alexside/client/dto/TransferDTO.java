package com.alexside.client.dto;

/**
 * Created by Alex on 07.05.2017.
 */
public class TransferDTO {
    private String sender;
    private String recipient;
    private double amount;
    private String description;
    public TransferDTO() {}
    public TransferDTO(String sender, String recipient, double amount, String description) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.description = description;
    }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
