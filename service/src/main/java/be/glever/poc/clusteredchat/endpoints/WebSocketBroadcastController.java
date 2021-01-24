package be.glever.poc.clusteredchat.endpoints;

import be.glever.poc.clusteredchat.messages.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class WebSocketBroadcastController {


    @GetMapping("/stomp-broadcast")

    public String getWebSocketBroadcast() {

        return "stomp-broadcast";

    }


    @MessageMapping("/broadcast")
    @SendTo("/topic/messages")
    public Message send(Message chatMessage){
        return new Message(chatMessage.getFrom(), chatMessage.getMessage(), "ALL");

    }

}