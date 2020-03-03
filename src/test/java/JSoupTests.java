import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSoupTests {

    @Test
    public void test_That_You_Get_Response_From_Web_Page(){

        Connection conn = Jsoup.connect("http://www.google.com");
        Assertions.assertNotEquals(null, conn);
    }
}
