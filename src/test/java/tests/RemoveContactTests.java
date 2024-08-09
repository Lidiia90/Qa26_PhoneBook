package tests;

import manager.HelperContact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("kate24@gmail.com").withPassword("kaT45#kit"));
        }
        app.getHelperContact().provideContacts();
        //app.getHelperContact().provideContacts(); //if list size<3 contacts--> add3 contacts
    }
    @Test
    public void removeFirstContact() {
        HelperContact helperContact = app.getHelperContact();
        int initialContactCount = helperContact.countOfContacts();
        helperContact.removeFirstContact();
        helperContact.pause(2000);
        int updatedContactCount = helperContact.countOfContacts();
        Assert.assertEquals(updatedContactCount, initialContactCount - 1, "The contact list size did not decrease by one after removing the first contact.");
    }
    @Test(groups = {"smoke"})
    public void removeAllContacts(){
        HelperContact helperContact = app.getHelperContact();
        int initialContactCount = helperContact.countOfContacts();
        for(int i = 0; i < initialContactCount; i++){
            helperContact.removeFirstContact();
            helperContact.pause(2000);
        }
        helperContact.pause(2000);
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
//        //"No Contacts here!"
    }
}