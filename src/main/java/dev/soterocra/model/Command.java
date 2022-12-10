package dev.soterocra.model;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private Long user;
    private String command;
    private List<String> replies = new ArrayList<>();
    public Command(Long user, String command) {
        this.user = user;
        this.command = command;
    }

    public Long getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getReplies() {
        return replies;
    }

}
