package be.glever.poc.clusteredchat.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

public class CustomAuthentication extends AbstractAuthenticationToken {
    private final String principal;

    public CustomAuthentication(String principal) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
