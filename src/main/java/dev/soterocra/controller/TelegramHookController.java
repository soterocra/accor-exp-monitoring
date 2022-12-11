package dev.soterocra.controller;

import dev.soterocra.controller.dto.TelegramMessageDTO;
import dev.soterocra.model.Command;
import dev.soterocra.model.Item;
import dev.soterocra.model.Result;
import dev.soterocra.model.User;
import dev.soterocra.service.CompareService;
import dev.soterocra.service.ItemService;
import dev.soterocra.service.ScraperService;
import dev.soterocra.service.UserService;
import dev.soterocra.usecase.UpdateTableUseCase;
import dev.soterocra.usecase.telegram.intentions.StrategyFactory;
import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Path("/accor-exp")
public class TelegramHookController {

    @Inject
    ScraperService scraperService;

    @Inject
    ItemService itemService;

    @Inject
    CompareService compareService;

    @Inject
    UpdateTableUseCase updateTableUseCase;

    @Inject
    UserService userService;

    @Inject
    StrategyFactory strategyFactory;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Item> execute() throws IOException {

        Set<Item> itens = scraperService.execute();

        return itens;
    }

    @GET
    @Path("/database")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Item> findAll() {
        return itemService.findAll();
    }

    @GET
    @Path("/trigger")
    @Produces(MediaType.APPLICATION_JSON)
    public Result trigger() {
        return updateTableUseCase.refresh();
    }

    @GET
    @Path("/trigger-user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> triggerUser() {
        return userService.findAll();
    }

    @POST
    @Path("/telegram/webhook")
    @Produces(MediaType.APPLICATION_JSON)
    public void telegramWebhook(TelegramMessageDTO telegramMessageDTO) {
        Log.info(telegramMessageDTO);

        Command command = new Command(telegramMessageDTO.getMessage().getChat().getId(), telegramMessageDTO.getMessage().getText());

        strategyFactory.getStrategy(command).apply(command);
    }

}