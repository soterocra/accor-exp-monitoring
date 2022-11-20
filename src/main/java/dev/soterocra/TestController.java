package dev.soterocra;

import dev.soterocra.entity.ItemEntity;
import dev.soterocra.service.ItemService;
import dev.soterocra.service.ScraperService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/accor-exp")
public class TestController {

    @Inject
    ScraperService scraperService;

    @Inject
    ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> execute() throws IOException {

        List<Map<String, String>> itens = scraperService.execute();

        return itens;
    }

    @GET
    @Path("/database")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemEntity> findAll() {
        return itemService.findAll();
    }


}