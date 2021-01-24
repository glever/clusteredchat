package be.glever.poc.clusteredchat;

import be.glever.poc.clusteredchat.config.SecurityConfiguration;
import be.glever.poc.clusteredchat.config.StompConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SecurityConfiguration.class, StompConfig.class})
public class ClusteredchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClusteredchatApplication.class, args);
    }

}
