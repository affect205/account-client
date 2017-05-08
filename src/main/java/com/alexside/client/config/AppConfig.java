package com.alexside.client.config;

import com.alexside.client.event.AccountSearchEvent;
import com.alexside.client.event.OperationSearchEvent;
import com.alexside.client.gui.components.AccountSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by Alex on 08.05.2017.
 */
@Configuration
public class AppConfig {
    public static final String ACCOUNT_SEARCH = "account-search";
    public static final String OPERATION_SEARCH = "operation-search";

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    @Qualifier(ACCOUNT_SEARCH)
    public AccountSearch accountSearch() {
        Consumer<String> callback = (String accountNum) -> {
            eventPublisher.publishEvent(new AccountSearchEvent(this, accountNum));
        };
        AccountSearch accountSearch = new AccountSearch(callback);
        return accountSearch;
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    @Qualifier(OPERATION_SEARCH)
    public AccountSearch operationSearch() {
        Consumer<String> callback = (String accountNum) -> {
            eventPublisher.publishEvent(new OperationSearchEvent(this, accountNum));
        };
        AccountSearch accountSearch = new AccountSearch(callback);
        return accountSearch;
    }
}
