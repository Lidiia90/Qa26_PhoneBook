package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("kate24@gmail.com").withPassword("kaT45#kit"));
        }
    }
    @Test
    public void addNewContactSuccessAllFields(){
    int i = new Random().nextInt(1000)+1000;

       Contact contact = Contact.builder()
                .Name("Kate"+i)
                .LastName("Serova")
                .Phone("12345678"+i)
                .email("kate" + i + "@gmail.com")
                .Address("Tel aviv, Israel")
                .description("work")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
    }

