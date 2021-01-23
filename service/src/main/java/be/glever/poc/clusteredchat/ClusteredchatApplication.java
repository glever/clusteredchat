package be.glever.poc.clusteredchat;

import be.glever.poc.clusteredchat.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class})
public class ClusteredchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusteredchatApplication.class, args);
    }

}
