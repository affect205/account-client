package com.alexside.client.dto;

/**
 * Created by Alex on 07.05.2017.
 */
public class OperationDTO {
    private int num;
    private long id;
    private String type;
    private String sender;
    private String recipient;
    private long date;
    private double amount;
    private String description;
    public OperationDTO() {}
    public OperationDTO(int num, long id, String type, String sender, String recipient, long date, double amount, String description) {
        this.num = num;
        this.id = id;
        this.type = type;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
