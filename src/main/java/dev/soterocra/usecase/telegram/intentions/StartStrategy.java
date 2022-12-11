package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import io.vertx.core.eventbus.EventBus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StartStrategy implements TelegramIntentionStrategy {

    @Inject
    EventBus bus;

    @Override
    public void apply(Command command) {
        command.getReplies().add("✅ A partir de agora você será notificado por aqui de todas as experiências All Accor!\n\nSua identificação na base do bot é: " + command.getUser());
        command.getReplies().add("Olá! \uD83D\uDE03\n\nEste bot foi criado com o intuito de pesquisar automaticamente as esperiências <b>All Accor</b> que foram inseridas ou removidas no site. Através dele você saberá das modificações em no máximo 5 minutos após serem feitas. \uD83C\uDF89\n\nDesenvolvi buscando ajudar a todos que se interessem pelas experiências All e com o tempo podemos adicionar mais e mais funcionalidades.\n\n Se você gostou do bot, te convido a \uD83D\uDCB0 <b>contribuir com os custos</b> \uD83D\uDCB8 de manutenção do projeto. Caso se sinta à vontade, deixarei abaixo minha chave aleatória Pix:");
        command.getReplies().add("83fa7c98-7eae-4cb6-a15e-bbcdd8fa3792");
        command.getReplies().add("⚠ Para tirar todas as suas dúvidas, não deixe de assistir o vídeo:\n\nhttps://youtu.be/jJ6PgjIVnQM");

        bus.request("telegram-received-message", command);
    }

    @Override
    public String getCommand() {
        return "/start";
    }

}
