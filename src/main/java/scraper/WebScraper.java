package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class WebScraper {

    public Elements getProducts(Document doc) {
        Elements products = doc.getElementsByClass("product");
        return products;
    }

    public Document goToProductInfo(Element product) throws IOException {
        Element link = product.getElementsByTag("a").first();
        String absHref = link.attr("abs:href");
        Document doc = Jsoup.connect(absHref).get();
        return doc;
    }

    public String getProductName(Document productInfo) {
        Element name = productInfo.getElementsByClass("productSummary").get(0);
        String productName = name.getElementsByTag("h1").text();
        return productName;
    }

    public BigDecimal getProductPrice(Document productInfo) {
        Element pricing = productInfo.getElementsByClass("pricePerUnit").first();
        String pricePerUnit = pricing.getElementsByTag("p").text().substring(0, 5).substring(1);
        double price = Double.parseDouble(pricePerUnit);
        BigDecimal bd = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_DOWN);
        return bd;
    }

    public String getProductDescription(Document productInfo) {
        String description = productInfo.getElementsByClass("productText")
                .get(0).getElementsByTag("p").text();
        return description;
    }

    public String getKcal(Document productInfo) {
        Element kCalData = productInfo.select("td:contains(kcal)").first();
        if (kCalData == null) {
            return "N/A";
        } else {
            return kCalData.text().substring(0, 2);
        }
    }

    public BigDecimal getTotalPrice(Elements products) throws IOException {
        WebScraper scraper = new WebScraper();
        double totalPrice = 0.0;
        for (Element product : products) {
            Document doc = scraper.goToProductInfo(product);
            double price = scraper.getProductPrice(doc).doubleValue();
            totalPrice += price;
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(totalPrice).setScale(2, RoundingMode.HALF_DOWN);
        return bigDecimal;
    }
}
