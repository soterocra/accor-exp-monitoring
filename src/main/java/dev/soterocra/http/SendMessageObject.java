package dev.soterocra.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageObject {

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("parse_mode")
    private final String parseMode = "html";

    public SendMessageObject(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParseMode() {
        return parseMode;
    }

    @Override
    public String toString() {
        return "{\"SendMessageObject\":{"
                + "\"chatId\":\"" + chatId + "\""
                + ", \"text\":\"" + text + "\""
                + ", \"parseMode\":\"" + parseMode + "\""
                + "}}";
    }
}
