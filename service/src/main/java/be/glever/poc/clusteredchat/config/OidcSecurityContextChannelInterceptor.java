package be.glever.poc.clusteredchat.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Stack;

public class OidcSecurityContextChannelInterceptor implements ChannelInterceptor {
    private static final SecurityContext EMPTY_CONTEXT = SecurityContextHolder.createEmptyContext();
    private static final ThreadLocal<Stack<SecurityContext>> originalContext = new ThreadLocal<>();

    private Authentication anonymous = new AnonymousAuthenticationToken("key", "anonymous",
            AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        setup(message);
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        this.cleanup();
    }

    private void setup(Message<?> message) {
        SecurityContext currentContext = SecurityContextHolder.getContext();
        Stack<SecurityContext> contextStack = originalContext.get();
        if (contextStack == null) {
            contextStack = new Stack<>();
            originalContext.set(contextStack);
        }
        contextStack.push(currentContext);
        Object user = message.getHeaders().get("user");
        Authentication authentication = getAuthentication(user);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private Authentication getAuthentication(Object user) {
        if ((user instanceof Authentication)) {
            return (Authentication) user;
        }
        return this.anonymous;
    }

    private void cleanup() {
        SecurityContextHolder.clearContext();
        originalContext.remove();
    }
}
