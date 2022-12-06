
package dev.soterocra.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "message_id",
    "from",
    "chat",
    "date",
    "text"
})
@Generated("jsonschema2pojo")
public class Message {

    @JsonProperty("message_id")
    private Long messageId;
    @JsonProperty("from")
    private From from;
    @JsonProperty("chat")
    private Chat chat;
    @JsonProperty("date")
    private Long date;
    @JsonProperty("text")
    private String text;

    @JsonProperty("message_id")
    public Long getMessageId() {
        return messageId;
    }

    @JsonProperty("message_id")
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @JsonProperty("from")
    public From getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(From from) {
        this.from = from;
    }

    @JsonProperty("chat")
    public Chat getChat() {
        return chat;
    }

    @JsonProperty("chat")
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @JsonProperty("date")
    public Long getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(Long date) {
        this.date = date;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{\"Message\":{"
                + "\"messageId\":\"" + messageId + "\""
                + ", \"from\":" + from
                + ", \"chat\":" + chat
                + ", \"date\":\"" + date + "\""
                + ", \"text\":\"" + text + "\""
                + "}}";
    }
}
