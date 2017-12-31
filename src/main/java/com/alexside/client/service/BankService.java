package com.alexside.client.service;

import com.alexside.client.dto.AccountDTO;
import com.alexside.client.dto.OperationDTO;
import com.alexside.client.dto.TransferDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.alexside.client.utils.AnyUtils.isEmpty;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

/**
 * Created by Alex on 08.05.2017.
 */
@Service
public class BankService {
    private static final Logger log = Logger.getLogger(BankService.class);

    @Value("${config.oauth2.resourceURI}")
    private String resourceURI;

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        log.debug("Try to get access token...");
        restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
        restTemplate.getAccessToken();
    }

    public AccountDTO getAccountByNumber(String number) {
        // return new AccountDTO(1L, "40817810099910004312", 43890.00);
        if (!isEmpty(resourceURI)) {
            AccountDTO response = restTemplate.getForObject(resourceURI + "/account/" + number, AccountDTO.class);
            return response;
        }
        return null;
    }

    public List<OperationDTO> getOperationsByNumber(String number) {
//        List<OperationDTO> list = new ArrayList<>();
//        list.add(new OperationDTO(1, 1L, "TRANSFER", "40817810099910004312", "40817810099910002289", Instant.now().toEpochMilli(), 22000.00, "Зачисление аванса"));
//        return list;
        if (!isEmpty(resourceURI)) {
            OperationDTO[] response = restTemplate.getForObject(resourceURI + "/operation/" + number, OperationDTO[].class);
            return asList(response);
        }
        return emptyList();
    }

    public boolean doTransfer(TransferDTO transfer) {
        //return true;
        if (!isEmpty(resourceURI)) {
            Boolean response = restTemplate.postForObject(resourceURI + "/operation/transfer", transfer, Boolean.class);
            return response == null ? false : response;
        }
        return false;
    }
}
