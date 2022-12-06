
package dev.soterocra.controller.dto;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "is_bot",
    "first_name",
    "username",
    "language_code",
    "is_premium"
})
@Generated("jsonschema2pojo")
public class From {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("is_bot")
    private Boolean isBot;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("username")
    private String username;
    @JsonProperty("language_code")
    private String languageCode;
    @JsonProperty("is_premium")
    private Boolean isPremium;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("is_bot")
    public Boolean getIsBot() {
        return isBot;
    }

    @JsonProperty("is_bot")
    public void setIsBot(Boolean isBot) {
        this.isBot = isBot;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("language_code")
    public String getLanguageCode() {
        return languageCode;
    }

    @JsonProperty("language_code")
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @JsonProperty("is_premium")
    public Boolean getIsPremium() {
        return isPremium;
    }

    @JsonProperty("is_premium")
    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    @Override
    public String toString() {
        return "{\"From\":{"
                + "\"id\":\"" + id + "\""
                + ", \"isBot\":\"" + isBot + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"languageCode\":\"" + languageCode + "\""
                + ", \"isPremium\":\"" + isPremium + "\""
                + "}}";
    }
}
