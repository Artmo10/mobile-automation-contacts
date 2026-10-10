package control;

import org.openqa.selenium.By;

public class TextBox extends BaseControl {
    public TextBox(By locator) {
        super(locator);
    }

    public void setText(String text){
        findControl();
        control.sendKeys(text);
    }
}