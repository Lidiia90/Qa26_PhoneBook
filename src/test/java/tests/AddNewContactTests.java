package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("kate24@gmail.com").withPassword("kaT45#kit"));
        }
    }

    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addNewContactSuccessAllFields(Contact contact){

        logger.info("Test with data: -->"+contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(groups = {"smoke","regress","retest"})
    public void addContactSuccessReqFields() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .Name("Kate"+i)
                .LastName("Serova")
                .Phone("12345678"+i)
                .email("kate" + i + "@gmail.com")
                .Address("Tel aviv, Israel")
                .build();

        logger.info("Tests run with data: --->" + contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
//        app.getHelperContact().pause(2000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
//for jenkins

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .Name("")
                .LastName("Ivanova")
                .Phone("123456789340")
                .email("ivanov@gmail.com")
                .Address("Tel aviv, Israel")
                .description("wrong name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .Name("Maria")
                .LastName("")
                .Phone("123456786745")
                .email("maria1@gmail.com")
                .Address("Tel aviv, Israel")
                .description("empty last name")
                .build();
        logger.info("Test with data: -->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .Name("Petya")
                .LastName("Petrov")
                .Phone("1234567867")
                .email("petya2@gmail.com")
                .Address("")
                .description("wrong address")
                .build();
        logger.info("Test with data: -->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongPhone(Contact contact){
//        Contact contact = Contact.builder()
//                .Name("Daria")
//                .LastName("Devi")
//                .Phone("")
//                .email("daria3@gmail.com")
//                .Address("Tel aviv, Israel")
//                .description("wrong phone")
//                .build();
        logger.info("Test with data: -->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test()
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .Name("Lora")
                .LastName("Gam")
                .Phone("1234567823")
                .email("lora4gmail.com")
                .Address("Tel aviv, Israel")
                .description("wrong email")
                .build();
        logger.info("Test with data: -->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));
    }
    @Test(dataProvider = "contactCSV",dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFieldsCSV(Contact contact) {

        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
//        app.getHelperContact().pause(2000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
}
