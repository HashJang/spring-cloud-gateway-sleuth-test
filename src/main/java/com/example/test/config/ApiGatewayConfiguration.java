package com.example.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.event.EnableBodyCachingEvent;
import org.springframework.cloud.gateway.filter.AdaptCachedBodyGlobalFilter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration(proxyBeanMethods = false)
public class ApiGatewayConfiguration {
    @Autowired
    private AdaptCachedBodyGlobalFilter adaptCachedBodyGlobalFilter;
    @Autowired
    private GatewayProperties gatewayProperties;

    @PostConstruct
    public void init() {
        gatewayProperties.getRoutes().forEach(routeDefinition -> {
            EnableBodyCachingEvent enableBodyCachingEvent = new EnableBodyCachingEvent(new Object(), routeDefinition.getId());
            adaptCachedBodyGlobalFilter.onApplicationEvent(enableBodyCachingEvent);
        });
    }
}
