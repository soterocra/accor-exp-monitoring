package dev.soterocra.usecase;

import dev.soterocra.model.Result;
import dev.soterocra.service.CompareService;
import dev.soterocra.service.ItemService;
import dev.soterocra.service.ScraperService;
import dev.soterocra.service.observer.ItemState;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateTableUseCase {

    private static final Logger LOG = Logger.getLogger(UpdateTableUseCase.class.getSimpleName());

    @Inject
    CompareService compareService;

    @Inject
    ItemService itemService;

    @Inject
    ScraperService scraperService;

    @Inject
    ItemState itemState;

    public Result refresh() {
        LOG.info("Iniciando atualização de itens.");

        LOG.info("Buscando itens no banco de dados.");
        var itensDatabase = itemService.findAll();

        LOG.info("Buscando itens no site online.");
        var itensOnline = scraperService.execute();

        LOG.info("Chamando comparação");
        Result result = compareService.execute(itensDatabase, itensOnline);

        LOG.info("Resultado: " + result);

        LOG.info("Realizando adições no observer");
        result.getAdded().forEach(itemState::notifyNew);

        LOG.info("Realizando deleções no observer");
        result.getRemoved().forEach(itemState::notifyRemoved);

        return result;
    }

}
