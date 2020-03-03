package scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraper {

    public Element getProduct(Document doc) {
        doc.getElementsByClass("product");
        return doc;
    }
}
