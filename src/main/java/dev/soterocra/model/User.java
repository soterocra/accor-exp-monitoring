package dev.soterocra.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long chatId;
    private String firstName;
    private String lastName;
    private String username;
    private final List<RegionEnum> preferredRegions = new ArrayList<>();

    public User() {
    }

    public User(Long chatId, String firstName, String lastName, String username) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RegionEnum> getPreferredRegions() {
        return preferredRegions;
    }

    @Override
    public String toString() {
        return "{\"User\":{"
                + "\"chatId\":\"" + chatId + "\""
                + ", \"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"username\":\"" + username + "\""
                + ", \"preferredRegions\":" + preferredRegions
                + "}}";
    }
}
