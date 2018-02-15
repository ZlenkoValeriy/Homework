package pageobjects.contacts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import pageobjects.BasePage;

public class ContactDetails extends BasePage {

    public ContactDetails(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isPhoneDisplayed(String phoneValue) {
        return driver.findElement(By.xpath("//*[@text='phone number']")).isDisplayed();
    }

    public boolean isEmailDisplayed(String emailValue) {
        // TODO: stabs
        return driver.findElement(By.xpath("//*[@text='phone number']")).isDisplayed();
    }

    public boolean isAddressDisplayed(String addressValue) {
        // TODO: stabs
        return driver.findElement(By.xpath("//*[@text='phone number']")).isDisplayed();
    }
}
