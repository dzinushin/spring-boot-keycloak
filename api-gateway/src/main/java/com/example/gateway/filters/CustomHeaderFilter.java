package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CustomHeaderFilter");

            logger.info(">>>>>>>>>>>>>>>> ctx: {}", ctx.getClass());
            Authentication auth = ctx.getAuthentication();
            logger.info(">>>>>>>>>>>>>>>> name: {}  ", auth.getName());

            var authorities = auth.getAuthorities();
            String authoritiesAsString = "";
            for(GrantedAuthority authority: authorities) {
                authoritiesAsString = authoritiesAsString + " " + authority.getAuthority();
            }
            logger.info(">>>>>>>>>>>>>>>>>>>>> authorities: {}", authorities);

            Object credentials = auth.getCredentials();
            logger.info(">>>>>>>>>>>>>>>>>> credentials class: {} value: {}", credentials.getClass(), credentials);

//            Object details = auth.getDetails();
//            logger.info(">>>>>>>>>>>>>>>>> details class: {}", details.getClass());

            Object principal = auth.getPrincipal();
            logger.info(">>>>>>>>>>>>>>>>>> principal: {}", principal.getClass());


            String userEmail = "";
            String subject = "";
            if (principal instanceof DefaultOidcUser oidcUser) {
                Map<String, Object> attributes = oidcUser.getAttributes();
                logger.info(">>>>>>>>>>>>>>> attributes: {}", attributes);
                Map<String, Object> claims = oidcUser.getClaims();
                logger.info(">>>>>>>>>>>>>>> claims: {}", claims);
                OidcUserInfo userInfo = oidcUser.getUserInfo();
                Map<String, Object> userInfoClaims = userInfo.getClaims();
                logger.info(">>>>>>>>>>>>>>> userInfo claims: {}", userInfoClaims);
                userEmail = (String) userInfoClaims.get("email");
            }

            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("x-user-id", auth.getName())
                    .header("x-user-email", userEmail)
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config {
        // Конфигурационные параметры, если нужны
    }
}