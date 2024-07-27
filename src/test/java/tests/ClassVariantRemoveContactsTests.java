package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClassVariantRemoveContactsTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("kate24@gmail.com").withPassword("kaT45#kit"));
        }
        app.getHelperContact().provideContacts();
    }

        @Test
        public void removeFirstContact() {
            Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
        }
        @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        }
    }

