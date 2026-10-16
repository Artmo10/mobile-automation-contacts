package tests;

import activities.contacts.ContactDetailActivity;
import activities.contacts.ContactEditorActivity;
import activities.contacts.ContactsTabActivity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import session.Session;
import utils.TestData;

public class ContactCrudTest {

    ContactsTabActivity contactsTab = new ContactsTabActivity();
    ContactEditorActivity contactEditor = new ContactEditorActivity();
    ContactDetailActivity contactDetail = new ContactDetailActivity();

    @AfterEach
    public void tearDown(){
        Session.getSession().closeSession();
    }

    @Test
    @DisplayName("Phone app - Create, read, update and delete a contact")
    public void contactCrud(){
        String firstName = "Arturo" + TestData.randomLetters(5);
        String lastName = "Appium";
        String updatedLastName = "Edited";
        String phone = "70012345";
        String fullName = firstName + " " + lastName;
        String updatedFullName = firstName + " " + updatedLastName;

        // CREATE - Save returns to Phone > Contacts list
        contactsTab.contactsTab.click();
        contactsTab.createNewContactButton.click();
        contactEditor.firstNameTextBox.setText(firstName);
        contactEditor.lastNameTextBox.setText(lastName);
        contactEditor.phoneTextBox.setText(phone);
        contactEditor.saveButton.click();
        Assertions.assertTrue(contactEditor.savedToast(fullName).isControlPresent(),
                "'Saved' toast was not shown after creating the contact");

        // READ
        Assertions.assertTrue(contactsTab.contactInList(fullName).isControlDisplayed(),
                "Contact is not listed in Phone > Contacts");

        // UPDATE - change last name
        contactsTab.contactInList(fullName).click();
        contactDetail.editButton.click();
        contactEditor.fieldWithValue(lastName).clear();
        contactEditor.lastNameTextBox.setText(updatedLastName);
        contactEditor.saveButton.click();
        // Save returns to the contact detail screen
        Assertions.assertEquals(updatedFullName, contactDetail.nameTitleLabel.getText(),
                "Contact name was not updated");

        // DELETE - returns to Phone > Contacts (empty state)
        contactDetail.moreOptionsButton.click();
        contactDetail.deleteOption.click();
        Assertions.assertEquals("Delete contact?", contactDetail.deleteDialogTitleLabel.getText(),
                "Delete confirmation dialog was not shown");
        contactDetail.confirmDeleteButton.click();
        Assertions.assertTrue(contactsTab.createNewContactButton.isControlDisplayed(),
                "Contacts list did not return to empty state");
        Assertions.assertTrue(contactsTab.contactInList(updatedFullName).isControlNotPresent(),
                "Contact still exists after delete");
    }
}