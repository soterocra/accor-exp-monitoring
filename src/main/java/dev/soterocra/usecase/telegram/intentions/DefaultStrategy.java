package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DefaultStrategy implements TelegramIntentionStrategy {

    @Inject
    EventBus bus;

    @Override
    public void apply(Command command) {
        command.getReplies().add("Desculpe, ainda não sei responder a isso.");

        bus.request("telegram-received-message", command);
    }

    @Override
    public String getCommand() {
        return "";
    }
}
