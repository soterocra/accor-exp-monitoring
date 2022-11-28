package dev.soterocra.entity;

import dev.soterocra.model.RegionEnum;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DynamoDbBean
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7227338441162712387L;

    public static final String TABLE_NAME = "accor-experiences-chats-dev";

    private Long chatId;
    private String firstName;
    private String lastName;
    private String username;
    private final List<RegionEnum> preferredRegions = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long chatId, String firstName, String lastName, String username) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    @DynamoDbAttribute("chat_id")
    @DynamoDbPartitionKey
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @DynamoDbAttribute("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDbAttribute("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDbAttribute("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDbAttribute("preferred_regions")
    public List<RegionEnum> getPreferredRegions() {
        return preferredRegions;
    }

    @Override
    public String toString() {
        return "{\"UserEntity\":{"
                + "\"chatId\":\"" + chatId + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"preferredRegions\":" + preferredRegions
                + "}}";
    }
}
