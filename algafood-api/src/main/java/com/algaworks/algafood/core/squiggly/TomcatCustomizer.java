package com.algaworks.algafood.core.squiggly;

// Referências:
// - https://stackoverflow.com/a/53613678
// - https://tomcat.apache.org/tomcat-8.5-doc/config/http.html
// - https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-configure-webserver

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 13.3. Limitando os campos retornados pela API com Squiggly<p>
 * @see  "https://github.com/felipem11/algaworks-api"
 * @see  "https://gist.github.com/thiagofa/ce48c08e4caae34c5dca0a7a5c252666"
 * @see  "https://github.com/bohnman/squiggly"
 * @author  Felipe Martins
 * @version 1.0
 * @since   2020-04-15
 */

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
    }

}