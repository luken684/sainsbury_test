package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class WebScraper {

    public Element getProduct(Document doc) {
        doc.getElementsByClass("product");
        return doc;
    }

    public Document goToProductInfo(Element product) throws IOException {
      Element link =  product.getElementsByTag("a").first();
      String relHref = link.attr("href");
      String absHref = link.attr("abs:href");
      Document doc = Jsoup.connect(absHref).get();
      return doc;
    }
}
