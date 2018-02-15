import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.contacts.ContactDetails;
import pageobjects.contacts.ContactsList;

import java.util.List;

public class ContactsTest extends BaseTest {

    // Test suite
    // - test 1
    // -- test class 1
    // --- test method 1
    // -- test class 2
    // - test 2
    // - test 3


    @BeforeMethod
    public void setUp() throws Exception {
        driver.launchApp();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.closeApp();
    }


    @DataProvider(name = "namesAndPhones")
    public static Object[][] namesAndPhones() {
        return new Object[][]{
                {"Garance Epperson", "+1(747)-8330134"},
//                {"Byron Workman", "+1(747)-8330134"},
//                {"Asasdsad", "+1(747)-8330134"},
//                {"adsadsadsadas", "+1(747)-8330134"},
//                {"acak asa", "+1(747)-8330134"},
//                {"Irma", "+1(747)-8330134"},
        };
    }

    @Test(testName = "Contact details verification",
            description = "[P0] C134234 - Contact details",
            groups = {"smoke", "regression"},
            dataProvider = "namesAndPhones")
    public void myFirstTest(String name, String phone) throws InterruptedException {
        driver.findElement(By.id("com.jayway.contacts:id/fab")).click();
        Thread.sleep(2000);

        String locator = String.format("//*[@text='%s']", name);
        System.out.println(locator);
        MobileElement element = driver.findElement(By.xpath(locator));
        element.click();

        // waiting

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement detailName = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementById("com.jayway.contacts:id/detail_name")));
        Assert.assertEquals(detailName.getText(), name);

        MobileElement phoneNumber = driver.findElementById("com.jayway.contacts:id/phonenumber");
        Assert.assertEquals(phoneNumber.getText(), phone);
    }

    @Test
    public void testMethodTwo() throws Exception {
        String locatorForContact = "com.jayway.contacts:id/name";
        List<MobileElement> elements = driver.findElements(By.id(locatorForContact));
        MobileElement lastElement;
        MobileElement firstElement;

        if (!elements.isEmpty()) {
            logger.info("Contacts were found");
            lastElement = elements.get(elements.size() - 1);
            firstElement = elements.get(0);
        } else {
            throw new IllegalStateException("No contacts!");
        }

        for (int i = 0; i < 10; i++) {
            List<MobileElement> contactsBeforeScroll = driver.findElements(By.id(locatorForContact));
            String lastContact = contactsBeforeScroll.get(contactsBeforeScroll.size() - 1).getText();
            logger.info(String.format("Last contact is %s", lastContact));

            new TouchAction(driver)
                    .press(lastElement)
                    .moveTo(firstElement)
                    .release()
                    .perform();

            List<MobileElement> contactsAfterScroll = driver.findElements(By.id(locatorForContact));
            String lastContactAfterScroll = contactsAfterScroll.get(contactsAfterScroll.size() - 1).getText();
            logger.info(String.format("Last contact after scroll is %s", lastContactAfterScroll));

            if (lastContact.equals(lastContactAfterScroll)) {
                logger.info("Test passed!");
                return;
            } else if (i == 9) {
                throw new IllegalStateException("Scroll is failed!");
            }
        }
    }

    @DataProvider(name = "searchParam")
    public static Object[][] searchParam() {
        return new Object[][]{
                {"en"},
                {"Garance Epperson"}
        };
    }

    @Test(testName = "myTersest",
            dataProvider = "searchParam")
    public void testMethodThree(String searchName) {
        ContactsList contactsListScreen = new ContactsList(driver);
        contactsListScreen.searchContact(searchName);
        List<MobileElement> elementList = contactsListScreen.getContacts();

        for (int i = 0; i < elementList.size(); i++) {
            String stroka = elementList.get(i).getText();
            System.out.println(stroka);
            Assert.assertTrue(stroka.contains(searchName));
        }

    }

    @Test
    public void testContactDetails() throws Exception {
        ContactsList contactsList = new ContactsList(driver);
        ContactDetails contactDetails = contactsList.tapOnContact("Byron Workman");
        boolean address = contactDetails.isAddressDisplayed("address");
        Assert.assertTrue(address);

        boolean phone = contactDetails.isAddressDisplayed("phone");
        Assert.assertTrue(phone);

        new ContactsList(driver).tapOnContact("");
    }

    class searchelementsinlist {
        String pole;

        public searchelementsinlist(String pole) {
            this.pole = pole;
        }

    }

}
