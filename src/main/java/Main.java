
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scraper.WebScraper;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws IOException, NullPointerException {
        Document doc = Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html").get();
        JSONArray productList = new JSONArray();

        WebScraper scraper = new WebScraper();
        Elements products = scraper.getProducts(doc);
        for(int x = 0; x < products.size(); x++){
            JSONObject temp = new JSONObject();
            Element product = products.get(x);
            Document productPage = scraper.goToProductInfo(product);
            String  name = scraper.getProductName(productPage);
            String description = scraper.getProductDescription(productPage);
            String kCalPer100 = scraper.getKcal(productPage);
            BigDecimal unitPrice = scraper.getProductPrice(productPage);

            temp.put("Product Name", name);
            temp.put("Description", description);
            temp.put("Price per Unit", unitPrice);
            temp.put("Kcal per 100g", kCalPer100);
            productList.add(temp);
        }
        JSONObject prices = new JSONObject();
        BigDecimal totalPrice = scraper.getTotalPrice(products);
        prices.put("Total Price", totalPrice);
        productList.add(totalPrice);


        System.out.println(productList.toString());


    }
}
