package dev.soterocra.service;

import dev.soterocra.controller.dto.TelegramMessageDTO;
import dev.soterocra.http.SendMessageObject;
import dev.soterocra.http.TelegramClient;
import dev.soterocra.model.Command;
import dev.soterocra.model.Item;
import dev.soterocra.service.observer.ItemObserver;
import dev.soterocra.service.observer.ItemState;
import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.ConsumeEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Unremovable
@ApplicationScoped
public class TelegramService implements ItemObserver {

    @ConfigProperty(name = "BOT_TOKEN")
    String botToken;

    @Inject
    @RestClient
    TelegramClient client;

    @Inject
    UserService userService;

    @Inject
    ItemService itemService;

    void startup(@Observes StartupEvent event, ItemState itemState) {
        itemState.addObserver(this);
    }

    public void sendMessage(Long chatId, String text) {
        client.sendMessage(botToken, new SendMessageObject(chatId, text));
    }

    @ConsumeEvent(value = "telegram-received-message", blocking = true)
    public void sendMessage(Command command) {
        command.getReplies().forEach(reply -> client.sendMessage(botToken, new SendMessageObject(command.getUser(), reply)));
    }

    public void sendCurrentItens(TelegramMessageDTO dto) {
        itemService.findAll().forEach(item -> {
            var text = "<b>Resultado da Busca</b> \uD83D\uDD0D \uD83D\uDD0D \uD83D\uDD0D \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
            this. sendMessage(dto.getMessage().getChat().getId(), text);
        });
    }

    @Override
    public void newItem(Item item) {
        Log.info("Novo Item recebido no observer do telegram. " + item);

        userService.findAll().parallelStream()
                .forEach(user -> {
                    Log.info("Enviando mensagem de adição para usuário: " + user);
//                    var text = "<b>Resultado da Busca</b> \uD83D\uDD0D \uD83D\uDD0D \uD83D\uDD0D \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    var text = "<b>Adição</b> ✅ ✅ ✅ \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    this.sendMessage(user.getChatId(), text);
                });
    }

    @Override
    public void removedItem(Item item) {
        Log.info("Item para remoção recebido no observer do telegram. " + item);

        userService.findAll().parallelStream()
                .forEach(user -> {
                    Log.info("Enviando mensagem de remoção para usuário: " + user);
//                    var text = "<b>Resultado da Busca</b> \uD83D\uDD0D \uD83D\uDD0D \uD83D\uDD0D \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    var text = "<b>Remoção</b> ❌ ❌ ❌ \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    this.sendMessage(user.getChatId(), text);
                });
    }
}
