package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScraper {

    public Elements getProducts(Document doc) {
      Elements products =  doc.getElementsByClass("product");
        return products;
    }

    public Document goToProductInfo(Element product) throws IOException {
      Element link =  product.getElementsByTag("a").first();
      String relHref = link.attr("href");
      String absHref = link.attr("abs:href");
      Document doc = Jsoup.connect(absHref).get();
      return doc;
    }
    public String getProductName(Document productInfo) {
        Element name = productInfo.getElementsByClass("productSummary").get(0);
        String productName = name.getElementsByTag("h1").text();
        return productName;
    }

    public Double getProductPrice(Document productInfo) {
        Element pricing = productInfo.getElementsByClass("pricePerUnit").first();
        String pricePerUnit = pricing.getElementsByTag("p").text().substring(0,5).substring(1);
        Double price = Double.parseDouble(pricePerUnit);
        return price;
    }
}
