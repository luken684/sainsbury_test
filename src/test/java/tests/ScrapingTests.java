package tests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scraper.WebScraper;


import java.io.IOException;

public class ScrapingTests {

    WebScraper scraper = new WebScraper();

    Document doc;
    {
        try {
            doc = Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_that_you_can_get_product(){
     Elements products = scraper.getProducts(doc);
        Assertions.assertNotNull(products);
    }

    @Test
    public void test_That_You_Can_Go_To_Product_Info_Page() throws IOException {
        Elements products = scraper.getProducts(doc);
        Element product = products.get(0);
        Document productInfo = scraper.goToProductInfo(product);
        Assertions.assertEquals(productInfo.title(), "Sainsbury's Strawberries 400g | Sainsbury's");
    }

    @Test
    public void test_That_You_Can_Get_A_Product_Name() throws IOException {
        Elements products = scraper.getProducts(doc);
        Element product = products.get(0);
        Document productInfo = scraper.goToProductInfo(product);
        String title = scraper.getProductName(productInfo);
        Assertions.assertEquals("Sainsbury's Strawberries 400g", title);
    }
    @Test
    public void test_That_You_Can_Get_Products_Price() throws IOException {
        Elements products = scraper.getProducts(doc);
        Element product = products.get(0);
        Document productInfo = scraper.goToProductInfo(product);
        Double pricePerUnit = scraper.getProductPrice(productInfo);
        Assertions.assertEquals(1.75, pricePerUnit);
    }
    @Test
    public void test_That_You_Can_Get_Products_Description() throws IOException {
        Elements products = scraper.getProducts(doc);
        Element product = products.get(0);
        Document productInfo = scraper.goToProductInfo(product);
        String description = scraper.getProductDescription(productInfo);
        Assertions.assertEquals("by Sainsbury's strawberries   ", description);
    }

    @Test
    public void test_That_You_Can_Get_Kcal() throws IOException {
        Elements products = scraper.getProducts(doc);
        Element product = products.get(0);
        Document productInfo = scraper.goToProductInfo(product);
        String kcal = scraper.getKcal(productInfo);
        Assertions.assertEquals("33", kcal);
    }


}
