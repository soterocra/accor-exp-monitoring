package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;

public interface TelegramIntentionStrategy {

    void apply(Command command);
    String getCommand();

}
