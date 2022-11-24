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

    List<Item> itens = new ArrayList<>();

    public List<Item> execute() {
        LOG.info("Iniciando busca.");

        Document doc = null;
        for (RegionEnum regionEnum : RegionEnum.values()) {

            LOG.info("Busca para a região: " + regionEnum);

            try {
                doc = Jsoup
                        .connect("https://limitlessexperiences.accor.com/all-experiences" + regionEnum.getPlaceForRegion())
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                        .header("cookie", regionEnum.getCookieForRegion())
                        .header("cache-control", "max-age=0")
                        .get();
            } catch (IOException e) {
                LOG.error("Não foi possível buscar as experiências atualizadas online.");
                throw new RuntimeException(e);
            }

            LOG.info("Documento baixado, inciando parse.");

            Elements products = doc.getElementsByClass("product-item");

            LOG.info("Produtos encontrados: " + products.size());

            products.forEach(element -> {

                String link = element.getElementsByClass("product-item-link").get(0).attr("href");
                String name = element.getElementsByClass("product-item-link").get(0).text();

                String price = "LEILÃO";
                try {
                    price = element.getElementsByClass("price").get(0).text();
                } catch (Exception e) {
                    LOG.info("Produto em provavél Leilão, verifique: " + link);
                }

                var item = new Item(link, name, price, regionEnum);

                LOG.info("Objeto encontrado: " + item);

                itens.add(item);

            });
        }

        return itens;
    }


}
