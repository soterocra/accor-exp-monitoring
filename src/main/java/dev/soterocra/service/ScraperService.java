package dev.soterocra.service;

import dev.soterocra.model.Item;
import dev.soterocra.model.RegionEnum;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ScraperService {

    private static final Logger LOG = Logger.getLogger(ScraperService.class.getSimpleName());

    public List<Item> execute() {
        LOG.info("Iniciando busca.");

        Document doc = null;
        try {
            doc = Jsoup
                    .connect("https://limitlessexperiences.accor.com/all-experiences?place_of_event=16224")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .header("cookie", RegionEnum.BRAZIL.getCookieForRegion())
                    .header("cache-control", "max-age=0")
                    .get();
        } catch (IOException e) {
            LOG.error("Não foi possível buscar as experiências atualizadas online.");
            throw new RuntimeException(e);
        }

        LOG.info("Documento baixado, inciando parse.");

        List<Item> itens = new ArrayList<>();

        Elements products = doc.getElementsByClass("product-item");

        LOG.info("Produtos encontrados: " + products.size());

        products.forEach(element -> {

            String link = element.getElementsByClass("product-item-link").get(0).attr("href");
            String name = element.getElementsByClass("product-item-link").get(0).text();
            String price = element.getElementsByClass("price").get(0).text();

            var item = new Item(link, name, price);

            LOG.info("Objeto encontrado: " + item);

            itens.add(item);

        });

        return itens;
    }


}
