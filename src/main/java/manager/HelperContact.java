package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        pause(500);
        click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("[placeholder='Name']"), contact.getName());
        type(By.cssSelector("[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("[placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
        click(By.cssSelector("div[class='add_form__2rsm2'] button b"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el : list) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public void provideContacts() {
        int count = countOfContacts();
        if (count < 3) {
            for (int i = count; i < 3; i++) {
                addOneContact();
            }
        }
    }

    public void addOneContact() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .Name("Kate" + i)
                .LastName("Serova")
                .Phone("12345678" + i)
                .email("kate" + i + "@gmail.com")
                .Address("Tel aviv, Israel")
                .description("work")
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContact();
    }
    public void removeAllContacts() {
        while (countOfContacts()!= 0) {
            removeContact();
        }
    }
    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1000);
    }

    public String getMessage() {
        return wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")).getText();
    }
    public void removeFirstContact() {
        List<WebElement> contactItems = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        if (!contactItems.isEmpty()) {
            contactItems.get(0).click();
        } else {
            System.out.println("No contact items found.");
        }
        click(By.xpath("//button[text()='Remove']"));
    }

    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contacts before remove is --->" + before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of Contacts before remove is --->" + after);
        return before-after;
    }
    public int countOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }
}


