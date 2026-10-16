package activities.contacts;

import control.Label;
import control.Button;
import control.TextBox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


public class ContactEditorActivity {

    public TextBox firstNameTextBox = new TextBox(AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")"));

    public TextBox lastNameTextBox = new TextBox(AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").text(\"Last name\")"));

    public TextBox phoneTextBox = new TextBox(AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")"));

    public Button saveButton = new Button(By.id("com.google.android.contacts:id/toolbar_button"));

    public TextBox fieldWithValue(String currentValue){
        return new TextBox(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").text(\"" + currentValue + "\")"));
    }

    public Label savedToast(String fullName){
        return new Label(By.xpath("//android.widget.Toast[@text=\"" + fullName + " saved\"]"));
    }
}