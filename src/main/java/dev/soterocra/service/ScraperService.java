package dev.soterocra.service;

import dev.soterocra.model.RegionEnum;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ScraperService {

    private static final Logger LOG = Logger.getLogger(ScraperService.class.getSimpleName());

    public List<Map<String, String>> execute() throws IOException {
        LOG.info("Iniciando busca.");

        Document doc = Jsoup
                .connect("https://limitlessexperiences.accor.com/all-experiences?place_of_event=16224")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .header("cookie", RegionEnum.BRAZIL.getCookieForRegion())
                .header("cache-control", "max-age=0")
                .get();

        LOG.info("Documento baixado, inciando parse.");

        List<Map<String, String>> itens = new ArrayList<>();

        Elements products = doc.getElementsByClass("product-item");

        LOG.info("Produtos encontrados: " + products.size());

        products.forEach(element -> {

            Map<String, String> item = new HashMap<>();

            String link = element.getElementsByClass("product-item-link").get(0).attr("href");
            String name = element.getElementsByClass("product-item-link").get(0).text();
            String price = element.getElementsByClass("price").get(0).text();

            item.put("link", link);
            item.put("name", name);
            item.put("price", price);

            LOG.info("Objeto encontrado: " + item);

            itens.add(item);

        });

        return itens;
    }


}
