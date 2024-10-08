package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int a = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User()
                .withEmail("Kate" + a + "@gmail.com")
                .withPassword("kaTe1234$");
        logger.info("Test with data: -->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        logger.info("openRegistrationForm");
        app.getHelperUser().fillLoginRegistrationForm(user);
        logger.info("fillLoginRegistrationForm");
        app.getHelperUser().submitRegistration();
        logger.info("submitRegistration");
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

    @Test(description = "Bug report #23456 Fixed")
    public void registrationWrongEmail() {
        User user = new User()
                .withEmail("Kategmail.com")
                .withPassword("kaTe1234$");
        logger.info("Test with data: -->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        //enabled = false делает невидимым это метод
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test(groups = {"smoke"})
    public void registrationWrongPassword() {
        User user = new User()
                .withEmail("Kate@gmail.com")
                .withPassword("kae14");
        logger.info("Test with data: -->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationExistsUser() {
        User user = new User()
                .withEmail("kate24@gmail.com")
                .withPassword("kaT45#kit");
        logger.info("Test with data: -->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }
}
