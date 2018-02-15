package pageobjects.contacts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.By;
import pageobjects.BasePage;
import utilities.PlatformManager;

import java.util.ArrayList;
import java.util.List;

public class ContactsList extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;

    public ContactsList(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();
    }

    @AndroidFindBy(id = "com.jayway.contacts:id/main_search")
    @iOSFindBy(accessibility = "Search for contact")
    private MobileElement searchField;

    @AndroidFindBy(id = "com.jayway.contacts:id/name")
    @iOSFindBy(xpath = "//XCUIElementTypeCell")
    private List<MobileElement> name;

    private String nameIOS = "//XCUIElementTypeStaticText[@name='%s']"; // xpath
    private String nameIdAndroid = ""; // id

    private String addButtonIOS = "//XCUIElementTypeButton[@name=\"Add\"]"; //xpath
    private String addButtonAndroid = "com.jayway.contacts:id/fab"; // id


    // methods for search

    public void searchContact(String contactName) {
        searchField.sendKeys(contactName);
    }

    public List<MobileElement> getContacts() {
        List<MobileElement> result = new ArrayList<MobileElement>();
        if (platform.equals(PlatformManager.Platform.IOS)) {
            for (MobileElement contact : name) {
                if (contact.isDisplayed()) result.add(contact);
            }
        } else if (platform.equals(PlatformManager.Platform.ANDROID)) {
            return name;
        }
        return result;
    }


    // methods for contacts verification

    public ContactDetails tapOnContact(String contactName) {
        String searchContact = "com.jayway.contacts:id/name";
        List<MobileElement> elements = driver.findElements(By.id(searchContact));
        for (MobileElement element : elements) {
            if (element.getText().equals(contactName)) {
                element.click();
                return new ContactDetails(driver);
            }
        }
        throw new IllegalStateException(String.format("Contact '%s' was not found!", contactName));
    }


}
