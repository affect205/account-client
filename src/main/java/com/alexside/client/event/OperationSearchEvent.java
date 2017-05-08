package com.alexside.client.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Alex on 08.05.2017.
 */
public class OperationSearchEvent extends ApplicationEvent {
    private String accountNumber;
    public OperationSearchEvent(Object source, String accountNumber) {
        super(source);
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber() { return accountNumber; }
}
