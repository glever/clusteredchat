package be.glever.poc.clusteredchat.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

/**
 * We live in an ideal world before the invention of lying.
 * Thus we trust that everyone is who he says he is.
 */
public class NaiveAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String username = ofNullable(request.getHeader("user")).orElse(null);
        SecurityContextHolder.getContext().setAuthentication(new CustomAuthentication(username));
        filterChain.doFilter(request, response);
    }
}
