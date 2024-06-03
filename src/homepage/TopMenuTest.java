package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        clickOnElement(By.partialLinkText(menu));
    }

    @Test
    public void verifyPageNavigation() {

        List<String> navList = new ArrayList<>();
        navList.add("Computers");
        navList.add("Electronics");
        navList.add("Apparel");
        navList.add("Digital downloads");
        navList.add("Books");
        navList.add("Jewelry");
        navList.add("Gift Cards");

        for (String list : navList) {
            selectMenu(list);
            String expHeading = list;
            String actualHeading = getTextFromElement(By.xpath("//h1[contains(text(),'"+list+"')]"));
            verifyText("Invalid Heading", expHeading, actualHeading);
        }
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
