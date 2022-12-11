package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import dev.soterocra.service.ItemService;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SearchStrategy implements TelegramIntentionStrategy {

    @Inject
    EventBus bus;

    @Inject
    ItemService itemService;

    @Override
    public void apply(Command command) {
        itemService.findAll().forEach(item -> command.getReplies().add("<b>Resultado da Busca</b> \uD83D\uDD0D \uD83D\uDD0D \uD83D\uDD0D \n\n<b>Nome do Evento:</b> " + item.getName() + "\n\n<b>Pontos Necessários:</b> \uD83D\uDCB0 " + item.getPrice() + "\n\n<b>Link:</b> \uD83C\uDF10 " + item.getLink() + "\n"));
        bus.request("telegram-received-message", command);
    }

    @Override
    public String getCommand() {
        return "/buscar";
    }

}
