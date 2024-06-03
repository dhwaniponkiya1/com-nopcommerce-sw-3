package computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));      //click on header tab computers
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));     //click on desktops

        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");     //select value from sort by
        Thread.sleep(2000);        //To avoid StaleElementReferenceException

        clickOnElement(By.xpath("//div[@data-productid='1']//div[2]//div[3]//div[2]//button[text()='Add to cart']"));     //click on add to cart
        String actualHeading = getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']"));       //getting text
        verifyText("Invalid heading", "Build your own computer", actualHeading);

        selectByValueFromDropDown(By.id("product_attribute_1"), "1");        //selecting processor value
        selectByValueFromDropDown(By.id("product_attribute_2"), "5");          //selecting RAM value
        clickOnElement(By.id("product_attribute_3_7"));          //selecting HDD value
        clickOnElement(By.id("product_attribute_4_9"));          //selecting OS value

        Thread.sleep(2000);

        while (!driver.findElement(By.id("product_attribute_5_10")).isSelected())
            clickOnElement(By.id("product_attribute_5_10"));            //selecting software checkbox

        clickOnElement(By.id("product_attribute_5_12"));          //selecting software checkbox

        Thread.sleep(2000);
        String expecPrice = "$1,475.00";
        String actualPrice = getTextFromElement(By.id("price-value-1"));        // get the total price
        verifyText("Price calculation is not correct", expecPrice, actualPrice);

        clickOnElement(By.id("add-to-cart-button-1")); // click on add to cart

        String cartConfirmationMsgExp = "The product has been added to your shopping cart";
        String cartConfirmationMsgActual = getTextFromElement(By.xpath("//p[@class='content']"));

        verifyText("Confirmation test is not as expected", cartConfirmationMsgExp, cartConfirmationMsgActual);
        clickOnElement(By.xpath("//span[@title='Close']"));        //CLose the bar with close button

        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='ico-cart']"));
        WebElement goToCartButton = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));

        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        //Mouse hover on shopping cart link, then Click on go to cart button
        actions.moveToElement(shoppingCartLink).moveToElement(goToCartButton).click().build().perform();

        String expShoppingCartText = "Shopping cart";       //verifying heading
        String acShoppingCartText = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        verifyText("Shopping Cart heading is invalid", expShoppingCartText, acShoppingCartText);

        clickOnElement(By.xpath("//div[@class='quantity up']"));         //Increase quantity

        String expTotal = "$2,950.00";       //verifying total
        String acTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Total is invalid", expTotal, acTotal);

        clickOnElement(By.id("termsofservice"));            //Click on checkbox 'I agree with...'
        clickOnElement(By.id("checkout"));            //Click on checkout button

        String expWelcomeMsg = "Welcome, Please Sign In!";       //verifying Welcome message
        String acWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        verifyText("Welcome message is invalid", expWelcomeMsg, acWelcomeMsg);

        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));            //Click on “CHECKOUT AS GUEST” Tab

        //Send form data
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "prime");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "testing");
        sendTextToElement(By.id("BillingNewAddress_Email"), "Prime@gmail.com");
        sendTextToElement(By.id("BillingNewAddress_Company"), "Prime Solutions");
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
        selectByValueFromDropDown(By.id("BillingNewAddress_StateProvinceId"), "0");
        sendTextToElement(By.id("BillingNewAddress_City"), "Solihull");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "36 Prime street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "B929QU");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07655898841");

        clickOnElement(By.xpath("//button[@onclick='if (!window.__cfRLUnblockHandlers) return false; Billing.save()']"));   //click continue
        clickOnElement(By.id("shippingoption_1"));          //click on next day air
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));      //Click continue
        clickOnElement(By.id("paymentmethod_1"));       //Select radio button credit card
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));      //Click continue
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");       //select mastercard from dropdown
        sendTextToElement(By.id("CardholderName"), "Prime Testing");
        sendTextToElement(By.id("CardNumber"), "5425233430109903");
        selectByValueFromDropDown(By.id("ExpireMonth"), "1");
        selectByValueFromDropDown(By.id("ExpireYear"), "2038");
        sendTextToElement(By.id("CardCode"), "565");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));      //Click on continue

        /*clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");            //Select master card from credit card dropdown
        sendTextToElement(By.id("CardholderName"), "Prime testing");     //sending card data
        sendTextToElement(By.id("CardNumber"), "5425233430109903");
        selectByValueFromDropDown(By.id("ExpireMonth"), "01");            //Select month from dropdown
        selectByValueFromDropDown(By.id("ExpireYear"), "2038");            //Select year card from dropdown
        sendTextToElement(By.id("CardCode"), "565");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));      //Click on continue button*/

        //Verifying text Payment Method: Credit Card and Shipping Method: Next Day Air
        String paymentText = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        verifyText("Payment method not valid", "Credit Card", paymentText);

        String shippingText = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        verifyText("Invalid shipping method", "Next Day Air", shippingText);

        //Verifying total amt
        String expTotalAmt = "$2,950.00";
        String actTotalAmt = getTextFromElement(By.xpath("//span[@class='value-summary']//strong"));
        verifyText("Total amount is not valid",expTotalAmt,actTotalAmt);

        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));      //Click on confirm
        verifyText("Invalid text","Thank you",getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']")));
        verifyText("Invalid text","Your order has been successfully processed!",getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")));

        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));     //Click on continue button

        verifyText("Invalid text","Welcome to our store",getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
