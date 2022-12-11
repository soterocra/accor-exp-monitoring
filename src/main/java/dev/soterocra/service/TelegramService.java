package dev.soterocra.service;

import dev.soterocra.http.SendMessageObject;
import dev.soterocra.http.TelegramClient;
import dev.soterocra.model.Command;
import io.quarkus.vertx.ConsumeEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TelegramService {

    @ConfigProperty(name = "BOT_TOKEN")
    String botToken;

    @Inject
    @RestClient
    TelegramClient client;

    @ConsumeEvent(value = "telegram-received-message", blocking = true)
    public void sendMessage(Command command) {
        command.getReplies().forEach(reply -> client.sendMessage(botToken, new SendMessageObject(command.getUser(), reply)));
    }

}
