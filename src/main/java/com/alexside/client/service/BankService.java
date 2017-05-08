package com.alexside.client.service;

import com.alexside.client.dto.AccountDTO;
import com.alexside.client.dto.OperationDTO;
import com.alexside.client.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

/**
 * Created by Alex on 08.05.2017.
 */
@Service
@Scope(SCOPE_SINGLETON)
public class BankService {
    @Autowired
    private RestTemplate restTemplate;

    public AccountDTO getAccountByNumber(String number) {
        return new AccountDTO(1L, "40817810099910004312", 43890.00);
    }

    public List<OperationDTO> getOperationsByNumber(String number) {
        List<OperationDTO> list = new ArrayList<>();
        list.add(new OperationDTO(1, 1L, "TRANSFER", "40817810099910004312", "40817810099910002289", Instant.now().toEpochMilli(), 22000.00, "Зачисление аванса"));
        return list;
    }

    public boolean doTransfer(TransferDTO transfer) {
        return true;
    }
}
