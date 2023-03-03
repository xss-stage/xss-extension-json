package org.stage.xss.json.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.stage.xss.core.spi.XssFilter;
import org.stage.xss.json.JsonXssFilter;

@Configuration
public class JsonXssFilterConfigurer{

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public XssFilter jsonXssFilter(){
        return new JsonXssFilter();
    }

}
