package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DonateStrategy implements TelegramIntentionStrategy {

    @Inject
    EventBus bus;

    @Override
    public void apply(Command command) {
        command.getReplies().add("Se você gostou do bot, te convido a \uD83D\uDCB0 <b>contribuir com os custos</b> \uD83D\uDCB8 de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:");
        command.getReplies().add("83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792");
        command.getReplies().add("Obrigado! \uD83D\uDE4F");

        bus.request("telegram-received-message", command);
    }

    @Override
    public String getCommand() {
        return "/doar";
    }

}
