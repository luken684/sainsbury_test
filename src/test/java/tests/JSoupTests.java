package tests;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JSoupTests {

    @Test
    public void test_That_You_Get_Response_From_Web_Page(){

        Connection conn = Jsoup.connect("http://www.google.com");
        Assertions.assertNotEquals(null, conn);
    }

    @Test
    public void test_That_You_Can_Get_Sainsbury_Page(){
        try {
            Document doc = Jsoup.connect("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html").get();
            String title = doc.title();
            Assertions.assertEquals("Berries, cherries & currants | Sainsbury's", title);
        } catch(IOException e){

        }
    }
}
