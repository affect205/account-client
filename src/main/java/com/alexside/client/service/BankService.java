package com.alexside.client.service;

import com.alexside.client.dto.AccountDTO;
import com.alexside.client.dto.OperationDTO;
import com.alexside.client.dto.TransferDTO;
import com.alexside.client.utils.AnyUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;

import static com.alexside.client.utils.AnyUtils.isEmpty;
import static java.util.Collections.emptyList;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

/**
 * Created by Alex on 08.05.2017.
 */
@Service
@Scope(SCOPE_SINGLETON)
public class BankService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApplicationContext context;
    @Value("${bank.service.host}")
    private String host;

    private Gson gson;

    @PostConstruct
    public void init() {
        gson = new Gson();
    }

    public AccountDTO getAccountByNumber(String number) {
        // return new AccountDTO(1L, "40817810099910004312", 43890.00);
        if (!isEmpty(host)) {
            String response = restTemplate.getForObject(host + "/account/" + number, String.class);
            if (response != null) {
                AccountDTO result = gson.fromJson(response, AccountDTO.class);
                return result;
            }
        }
        return null;
    }

    public List<OperationDTO> getOperationsByNumber(String number) {
//        List<OperationDTO> list = new ArrayList<>();
//        list.add(new OperationDTO(1, 1L, "TRANSFER", "40817810099910004312", "40817810099910002289", Instant.now().toEpochMilli(), 22000.00, "Зачисление аванса"));
//        return list;
        if (!isEmpty(host)) {
            String response = restTemplate.getForObject(host + "/operation/" + number, String.class);
            if (response != null) {
                List<OperationDTO> result = gson.fromJson(response, new TypeToken<List<OperationDTO>>(){}.getType());
                IntStream.range(0, result.size()).forEach(ndx -> result.get(ndx).setNum(ndx+1));
                return result;
            }
        }
        return emptyList();
    }

    public boolean doTransfer(TransferDTO transfer) {
        //return true;
        if (!isEmpty(host)) {
            String response = restTemplate.postForObject(host + "/operation/transfer", transfer, String.class);
            if (response != null) {
                ResponseEntity<Boolean> result = gson.fromJson(response, ResponseEntity.class);
                return result.getBody();
            }
        }
        return false;
    }
}
