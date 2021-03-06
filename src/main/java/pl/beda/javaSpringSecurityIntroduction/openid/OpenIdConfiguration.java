package pl.beda.javaSpringSecurityIntroduction.openid;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import pl.beda.javaSpringSecurityIntroduction.common.KeycloakResolverConfig;

@Configuration
@EnableWebSecurity
@Import(KeycloakResolverConfig.class)
public class OpenIdConfiguration extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(
                new SessionRegistryImpl());
    }

    @Autowired
    public void configureAuthManager(
            AuthenticationManagerBuilder authBuilder) {
        KeycloakAuthenticationProvider keycloakProvider =
                new KeycloakAuthenticationProvider();
        keycloakProvider.setGrantedAuthoritiesMapper(
                new SimpleAuthorityMapper());
        authBuilder.authenticationProvider(keycloakProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().antMatchers("/openid/*")
                .hasRole("user").anyRequest().permitAll();
    }
}
