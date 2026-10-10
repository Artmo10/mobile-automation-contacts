package device;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import session.Session;

import java.net.MalformedURLException;
import java.net.URI;

public class Android implements IDevice {

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/";

    @Override
    public AndroidDriver create() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Pixel 7 Pro");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "13.0");
        capabilities.setCapability("appium:appPackage", "com.google.android.dialer");
        capabilities.setCapability("appium:appActivity", ".extensions.GoogleDialtactsActivity");

        AndroidDriver driver;
        try {
            driver = new AndroidDriver(URI.create(APPIUM_SERVER_URL).toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Session.IMPLICIT_WAIT);
        return driver;
    }
}