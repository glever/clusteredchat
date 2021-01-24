package be.glever.poc.clusteredchat.messages;

import lombok.Value;

@Value
public class Message {
    String from;
    String to;
    String message;
}
