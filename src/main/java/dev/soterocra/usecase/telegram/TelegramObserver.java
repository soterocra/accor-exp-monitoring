package dev.soterocra.usecase.telegram;

import dev.soterocra.model.Command;
import dev.soterocra.model.Item;
import dev.soterocra.service.UserService;
import dev.soterocra.service.observer.ItemObserver;
import dev.soterocra.service.observer.ItemState;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class TelegramObserver implements ItemObserver {

    @Inject
    EventBus bus;

    @Inject
    UserService userService;

    void startup(@Observes StartupEvent event, ItemState itemState) {
        itemState.addObserver(this);
    }

    @Override
    public void newItem(Item item) {
        Log.info("Novo Item recebido no observer do telegram. " + item);

        userService.findAll().parallelStream()
                .forEach(user -> {
                    Log.info("Enviando mensagem de adição para usuário: " + user);
                    var text = "<b>Adição</b> ✅ ✅ ✅ \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    Command command = new Command(user.getChatId(), "scheduler");
                    command.getReplies().add(text);
                    bus.request("telegram-received-message", command);
                });
    }

    @Override
    public void removedItem(Item item) {
        Log.info("Item para remoção recebido no observer do telegram. " + item);

        userService.findAll().parallelStream()
                .forEach(user -> {
                    Log.info("Enviando mensagem de remoção para usuário: " + user);
                    var text = "<b>Remoção</b> ❌ ❌ ❌ \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n";
                    Command command = new Command(user.getChatId(), "scheduler");
                    command.getReplies().add(text);
                    bus.request("telegram-received-message", command);
                });
    }

}
