package dev.soterocra;

import dev.soterocra.controller.dto.TelegramMessageDTO;
import dev.soterocra.model.Item;
import dev.soterocra.model.Result;
import dev.soterocra.model.User;
import dev.soterocra.service.CompareService;
import dev.soterocra.service.ItemService;
import dev.soterocra.service.ScraperService;
import dev.soterocra.service.UserService;
import dev.soterocra.usecase.UpdateTableUseCase;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.core.eventbus.EventBus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/accor-exp")
public class TestController {

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
    EventBus bus;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> execute() throws IOException {

        List<Item> itens = scraperService.execute();

        return itens;
    }

    @GET
    @Path("/database")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> findAll() {
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
        bus.request("telegram-received-message", telegramMessageDTO);
    }

}