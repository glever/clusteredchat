package be.glever.poc.clusteredchat.endpoints;

import be.glever.poc.clusteredchat.security.CustomAuthentication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        CustomAuthentication auth = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName().isEmpty() ? "anonymous" : auth.getName();
        return "Hello, " + name;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @MessageMapping("/greeting")
    @SendTo("/queue/greeting")
    public String greeting(String message) throws Exception {
        return "message: " + message;
    }
}
