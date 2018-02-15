import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static AppiumDriver<MobileElement> driver;
    protected static Logger logger = LoggerFactory.getLogger(ContactsTest.class);
    protected static String platform;
    protected static File appFile;

    @BeforeClass
    public void beforeTestMethod() throws MalformedURLException {
        String pathToOurDir = System.getProperty("user.dir");
        platform = System.getProperty("platform", "Android").toLowerCase();
        if (ContactsTest.platform.equals("android")) {
            appFile = new File(pathToOurDir + "/app/android/Contacts.apk");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); //cmd + d
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Any");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium"); // optional
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        } else if (ContactsTest.platform.equals("ios")) {
            appFile = new File(pathToOurDir + "/app/ios/Contacts.app");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
//            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }
}
