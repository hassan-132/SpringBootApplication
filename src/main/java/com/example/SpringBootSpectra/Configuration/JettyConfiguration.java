package com.example.SpringBootSpectra.Configuration;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class JettyConfiguration implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {
    @Override
    public void customize(JettyServletWebServerFactory factory) {
        factory.setPort(8070);
        factory.setContextPath("");
    }

}
