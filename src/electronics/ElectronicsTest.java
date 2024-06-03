package electronics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        mouseHoverToElement(By.linkText("Electronics"));
        mouseHoverAndClickOnElement(By.linkText("Cell phones"));

        String expCellPhonesHeading = "Cell phones";
        String actCellPhonesHeading = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyText("Cell phones heading invalid", expCellPhonesHeading, actCellPhonesHeading);          //verify heading
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
        mouseHoverToElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));

        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));

        String expCellPhonesHeading = "Cell phones";
        String actCellPhonesHeading = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyText("Cell phones heading invalid", expCellPhonesHeading, actCellPhonesHeading);          //verify heading

        clickOnElement(By.xpath("//a[normalize-space()='List']"));      //Click on list view

        Thread.sleep(1000);
        clickOnElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Nokia Lumia 1020')]"));      //Click on nokia
        String expProductText = "Nokia Lumia 1020";
        String actProductText = getTextFromElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        verifyText("Invalid product name in heading", expProductText, actProductText);      //Verifying Text

        String expPrice = "$349.00";
        String actPrice = getTextFromElement(By.id("price-value-20"));
        verifyText("Invalid price", expPrice, actPrice);        //Verify price

        driver.findElement(By.id("product_enteredQuantity_20")).clear();
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");     //Change quantity to 2

        clickOnElement(By.id("add-to-cart-button-20"));         //Click on add to cart

        String expSuccessCartText = "The product has been added to your shopping cart";
        String actSuccessCartText = getTextFromElement(By.className("content"));
        verifyText("Invalid message", expSuccessCartText, actSuccessCartText);

        clickOnElement(By.className("close"));      //close the message

        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='ico-cart']"));
        WebElement goToCartButton = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));

        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        //Mouse hover on shopping cart link, then Click on go to cart button
        actions.moveToElement(shoppingCartLink).moveToElement(goToCartButton).click().build().perform();

        String expShoppingCartText = "Shopping cart";       //verifying heading
        String acShoppingCartText = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        verifyText("Shopping Cart heading is invalid", expShoppingCartText, acShoppingCartText);

        /*String expQuantity = "2";
        String actQuantity = getTextFromElement(By.cssSelector("input.qty-input"));
        verifyText("Invalid quantity", expQuantity, actQuantity);*/

        String expTotal = "$698.00";       //verifying total
        String acTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Total is invalid", expTotal, acTotal);

        clickOnElement(By.id("termsofservice"));            //Click on checkbox 'I agree with...'
        clickOnElement(By.id("checkout"));            //Click on checkout button

        String expWelcomeMsg = "Welcome, Please Sign In!";       //verifying Welcome message
        String acWelcomeMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        verifyText("Welcome message is invalid", expWelcomeMsg, acWelcomeMsg);

        clickOnElement(By.xpath("//button[normalize-space()='Register']"));         //Click on register

        String expRegHeading = "Register";
        String actRegHeading = getTextFromElement(By.xpath("//h1[normalize-space()='Register']"));
        verifyText("Invalid register heading", expRegHeading, actRegHeading);

        clickOnElement(By.id("gender-female"));
        sendTextToElement(By.id("FirstName"), "Dhwani");
        sendTextToElement(By.id("LastName"), "Ponkiya");
        selectByValueFromDropDown(By.xpath("//select[@name='DateOfBirthDay']"), "14");
        selectByValueFromDropDown(By.xpath("//select[@name='DateOfBirthMonth']"), "3");
        selectByValueFromDropDown(By.xpath("//select[@name='DateOfBirthYear']"), "2003");
        sendTextToElement(By.id("Email"), "test12@gmail.com");
        sendTextToElement(By.id("Password"), "test22");
        sendTextToElement(By.id("ConfirmPassword"), "test22");
        clickOnElement(By.id("register-button"));

        String expSuccessRegText = "Your registration completed";
        String actSuccessRegText = getTextFromElement(By.className("result"));
        verifyText("Invalid text", expSuccessRegText, actSuccessRegText);

        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));          //click continue
        String expCartText = "Shopping cart";
        String actCartText = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        verifyText("Invalid cart heading", expCartText, actCartText);

        clickOnElement(By.id("termsofservice"));            //Click on checkbox 'I agree with...'
        clickOnElement(By.id("checkout"));            //Click on checkout button

        Thread.sleep(1000);
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
        sendTextToElement(By.id("BillingNewAddress_City"), "Solihull");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "36 Prime street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "B929QU");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07655898841");

        clickOnElement(By.xpath("//button[@onclick='if (!window.__cfRLUnblockHandlers) return false; Billing.save()']"));   //click continue
        clickOnElement(By.id("shippingoption_2"));          //click on 2nd day air
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));      //Click continue
        clickOnElement(By.id("paymentmethod_1"));       //Select radio button credit card
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));      //Click continue
        selectByValueFromDropDown(By.id("CreditCardType"), "visa");       //select visa from dropdown
        sendTextToElement(By.id("CardholderName"), "Prime Testing");
        sendTextToElement(By.id("CardNumber"), "5425233430109903");
        selectByValueFromDropDown(By.id("ExpireMonth"), "1");
        selectByValueFromDropDown(By.id("ExpireYear"), "2038");
        sendTextToElement(By.id("CardCode"), "565");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));      //Click on continue

        //Verifying text Payment Method: Credit Card and Shipping Method: 2nd Day Air
        String paymentText = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        verifyText("Payment method not valid", "Credit Card", paymentText);

        String shippingText = getTextFromElement(By.xpath("//span[normalize-space()='2nd Day Air']"));
        verifyText("Invalid shipping method", "2nd Day Air", shippingText);

        //Verifying total amt
        String expTotalAmt = "$698.00";
        String actTotalAmt = getTextFromElement(By.xpath("//span[@class='value-summary']//strong"));
        verifyText("Total amount is not valid",expTotalAmt,actTotalAmt);

        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));      //Click on confirm
        verifyText("Invalid text","Thank you",getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']")));
        verifyText("Invalid text","Your order has been successfully processed!",getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")));

        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));     //Click on continue button

        verifyText("Invalid text","Welcome to our store",getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']")));

        clickOnElement(By.xpath("//a[normalize-space()='Log out']"));           //click logout
        String expUrl ="https://demo.nopcommerce.com/";
        verifyText("NOT redirected to homepage", expUrl, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
//        closeBrowser();
    }
}
