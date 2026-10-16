package activities.contacts;

import control.Button;
import control.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


public class ContactsTabActivity {

    public Button contactsTab = new Button(By.id("com.google.android.dialer:id/tab_contacts"));

    public Button createNewContactButton = new Button(By.id("com.google.android.dialer:id/empty_content_view_action"));

    private static final String CONTACT_NAME_ID = "com.google.android.dialer:id/contact_name";

    public Label contactInList(String fullName){
        return new Label(AppiumBy.androidUIAutomator(
                "new UiSelector().resourceId(\"" + CONTACT_NAME_ID + "\").text(\"" + fullName + "\")"));
    }
}