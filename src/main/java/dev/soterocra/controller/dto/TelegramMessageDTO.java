
package dev.soterocra.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "update_id",
    "message"
})
public class TelegramMessageDTO {

    @JsonProperty("update_id")
    private Long updateId;
    @JsonProperty("message")
    private Message message;

    @JsonProperty("update_id")
    public Long getUpdateId() {
        return updateId;
    }

    @JsonProperty("update_id")
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"TelegramMessageDTO\":{"
                + "\"updateId\":\"" + updateId + "\""
                + ", \"message\":" + message
                + "}}";
    }
}
