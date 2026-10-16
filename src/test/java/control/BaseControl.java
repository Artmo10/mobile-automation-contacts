package control;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import session.Session;

import java.time.Duration;

public class BaseControl {

    protected WebElement control;
    protected By locator;

    public BaseControl(By locator){
        this.locator = locator;
    }

    public void findControl(){
        control = Session.getSession().getDevice().findElement(locator);
    }

    public void click(){
        findControl();
        control.click();
    }

    public String getText(){
        findControl();
        return control.getText();
    }

    public boolean isControlDisplayed(){
        findControl();
        return control.isDisplayed();
    }

    public boolean isControlNotPresent(){
        AppiumDriver driver = Session.getSession().getDevice();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            return driver.findElements(locator).isEmpty();
        } finally {
            driver.manage().timeouts().implicitlyWait(Session.IMPLICIT_WAIT);
        }
    }

    public boolean isControlPresent(){
        return !Session.getSession().getDevice().findElements(locator).isEmpty();
    }
}