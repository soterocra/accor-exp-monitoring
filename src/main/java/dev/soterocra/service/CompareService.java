package dev.soterocra.service;

import dev.soterocra.model.Result;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CompareService {

    private static final Logger LOG = Logger.getLogger(CompareService.class.getSimpleName());

    @Inject
    ItemService itemService;

    @Inject
    ScraperService scraperService;

    public Result execute() {
        var itensDatabase = itemService.findAll();
        var itensOnline = scraperService.execute();

        // Todos os itens do online que não existem no database foram adicionados.
        var addedItens = itensOnline.stream().filter(item -> !itensDatabase.contains(item)).toList();

        //Todos os itens do database que não existem online foram removidos.
        var removedItens = itensDatabase.stream().filter(item -> !itensOnline.contains(item)).toList();

        //Itens iguais nas duas listas foram mantidos.
        var sustainedItens = itensDatabase.stream().filter(itensOnline::contains).toList();

        var result = new Result(addedItens, removedItens, sustainedItens);

        LOG.info("Resultado da comparação: " + result);

        return result;
    }

}
