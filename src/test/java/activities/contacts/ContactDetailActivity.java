package activities.contacts;

import control.Button;
import control.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


public class ContactDetailActivity {

    public Label nameTitleLabel = new Label(By.id("com.google.android.contacts:id/large_title"));

    public Button editButton = new Button(By.id("com.google.android.contacts:id/menu_insert_or_edit"));

    public Button moreOptionsButton = new Button(AppiumBy.accessibilityId("More options"));

    public Button deleteOption = new Button(AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"com.google.android.contacts:id/title\").text(\"Delete\")"));

    public Label deleteDialogTitleLabel = new Label(By.id("com.google.android.contacts:id/alertTitle"));

    public Button confirmDeleteButton = new Button(By.id("android:id/button1"));
}