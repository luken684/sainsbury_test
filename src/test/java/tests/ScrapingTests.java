package tests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
     Element product = scraper.getProduct(doc);
        Assertions.assertNotNull(product);
    }

    @Test
    public void test_That_You_Can_Go_To_Product_Info_Page() throws IOException {
        Element product = scraper.getProduct(doc);
        Document productInfo = scraper.goToProductInfo(product);
        Assertions.assertEquals(productInfo.title(), "Sainsbury's Strawberries 400g | Sainsbury's");
    }

    @Test
    public void test_That_You_Can_Get_A_Product_Name() throws IOException {
        Element product = scraper.getProduct(doc);
        Document productInfo = scraper.goToProductInfo(product);
        Element name = productInfo.getElementsByClass("productSummary").first();

       Assertions.assertNotNull(name);
    }
}
