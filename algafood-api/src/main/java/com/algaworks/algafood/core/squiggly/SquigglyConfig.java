package com.algaworks.algafood.core.squiggly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 13.3. Limitando os campos retornados pela API com Squiggly<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @see  "https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666"
 * @see  "https://github.com/bohnman/squiggly"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Configuration
public class SquigglyConfig {

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper){
//        Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos, null"));
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider());

        var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
        var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterRegistration.setFilter(new SquigglyRequestFilter());
        filterRegistration.setOrder(1);

        filterRegistration.setUrlPatterns(urlPatterns);

        return filterRegistration;

    }


}
