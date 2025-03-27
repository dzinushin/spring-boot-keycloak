package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomHeaderFilter extends AbstractGatewayFilterFactory<CustomHeaderFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(CustomHeaderFilter.class);

    public CustomHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> ReactiveSecurityContextHolder.getContext().flatMap(ctx -> {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Hello");
            Authentication authentication = ctx.getAuthentication();
            logger.info(">>>>>>>>>>>>>>>> name: {}  ", authentication.getName());
            Object principal = authentication.getPrincipal();
            logger.info(">>>>>>>>>>>>>>>>>> principal: {}", principal.getClass());


            String userEmail = "";
            if (principal instanceof OidcUser oidcUser) {
                OidcUserInfo userInfo = oidcUser.getUserInfo();
                Map<String, Object> claims = userInfo.getClaims();
                logger.info(">>>>>>>>>>>>>>> userInfo claims: {}", claims);
                userEmail = (String) claims.get("email");
            }

            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("x-user-email", userEmail)
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {
        // Конфигурационные параметры, если нужны
    }
}