package com.softserve.logeek.config.init;

import com.softserve.logeek.config.converter.JSONConverter;
import com.softserve.logeek.config.merge.ConfigMerger;
import com.softserve.logeek.config.request.ConfigRequester;
import com.softserve.logeek.config.request.ResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ConfigRequester requester() {
        return new ConfigRequester();
    }

    @Bean
    public ResponseParser parser() {
        return new ResponseParser();
    }

    @Bean
    public ConfigMerger merger() {
        return new ConfigMerger();
    }

    @Bean
    public JSONConverter converter() {
        return new JSONConverter();
    }
}
