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
    
}
