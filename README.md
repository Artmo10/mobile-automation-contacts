# Mobile Automation – Contacts CRUD (Android)

Automated end-to-end test that performs a full **CRUD** on a contact using the default **Phone by Google** app on an Android emulator, built with **Appium + Java + JUnit 5** following the **Page Object Model**.

## Test case

**`ContactCrudTest` – Phone app: Create, read, update and delete a contact**

| Step | Action | Expected result |
|------|--------|-----------------|
| Create | Phone › Contacts › *Create new contact*, fill First name, Last name, Phone and Save | Toast `"<First> <Last> saved"` is shown |
| Read | Back on the Contacts list | The new contact appears in the list |
| Update | Open the contact › Edit › change Last name › Save | Contact header shows the updated name |
| Delete | ⋮ More options › Delete › confirm | Dialog `"Delete contact?"` is shown; the list returns to its empty state and the contact no longer exists |

**Precondition:** the device has **no saved contacts** (the *Create new contact* button is the list's empty-state action). The test deletes the contact it creates, so it leaves the device ready for the next run.

## Tech stack

- Java + Gradle (wrapper included)
- Appium Java Client 10.1.1 (Selenium 4)
- UiAutomator2 driver
- JUnit 5

## Project structure

```
src/test/java/
├── activities/contacts/     # Page objects (one class per screen)
│   ├── ContactsTabActivity.java     # Phone app – Contacts tab and list
│   ├── ContactEditorActivity.java   # Create / edit contact form
│   └── ContactDetailActivity.java   # Contact detail, edit and delete
├── control/                 # Reusable UI controls (Button, Label, TextBox)
├── device/                  # Device factory and Android capabilities
├── session/                 # Singleton Appium session
├── tests/
│   └── ContactCrudTest.java
└── utils/
    └── TestData.java        # Random test data
```

## App under test

| Capability | Value |
|------------|-------|
| `appPackage` | `com.google.android.dialer` |
| `appActivity` | `.extensions.GoogleDialtactsActivity` |

The create/edit and detail screens are rendered by the Contacts app (`com.google.android.contacts`) within the same session.

## Prerequisites

- JDK installed
- Android SDK with an emulator **with Google Play** (configured: Pixel 7 Pro, Android 13, `emulator-5554`)
- Appium server with the UiAutomator2 driver:
  ```bash
  npm install -g appium
  appium driver install uiautomator2
  ```

Device settings live in `src/test/java/device/Android.java`. Adjust `deviceName`, `udid` and `platformVersion` to match your emulator.

## How to run

1. Start the emulator and check it is listed: `adb devices`
2. Start the Appium server: `appium` (default `http://127.0.0.1:4723/`)
3. Run the test:
   ```bash
   ./gradlew test --tests tests.ContactCrudTest
   ```
4. HTML report: `build/reports/tests/test/index.html`

## Implementation notes

- **Locators** were taken from Appium Inspector, preferring `resource-id`, then accessibility id, then UiSelector. Form fields have no id, so they are located by their hint text (`First name`, `Last name`, `Phone`).
- **Toast validation** uses a single `findElements` lookup (`isControlPresent`), because toasts disappear in ~2–3 s and a second call on the element throws `StaleElementReferenceException`.
- **Editing a field** is done in two lookups (clear by current value, then type using the hint locator), because UiAutomator2 re-resolves the element with its original selector on every action.
- **Contact names use letters only**: the Dialer wraps digit sequences in names with invisible bidi characters (U+202A / U+202C), which breaks exact-text locators.
