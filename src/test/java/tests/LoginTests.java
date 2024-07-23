package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        //if SIGN OUT present ----> logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method logout finish");
        }
    }

    @Test
    public void loginSuccess() {
        logger.info("Start test with name 'loginSuccess'");
        logger.info("Test data ---> email: 'kate24@gmail.com' & password: 'kaT45#kit'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginForm("kate24@gmail.com", "kaT45#kit");
        app.getHelperUser().submitLogin();

        // Assert.assertEquals();
        // Assert.assertNotEquals();
        // Assert.assertTrue();
        // Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");

    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test data ---> email: 'kate24@gmail.com' & password: 'kaT45#kit'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginForm("kate24@gmail.com", "kaT45#kit");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void loginWrongEmail() {
        logger.info("Test data ---> email: 'kate24gmail.com' & password: 'kaT45#kit'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginForm("kate24gmail.com", "kaT45#kit");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test data ---> email: 'kate24@gmail.com' & password: 'kakit'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginForm("kate24@gmail.com", "kakit");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginUnregisteredUser() {
        logger.info("Test data ---> email: 'lida24@gmail.com' & password: 'daT45#li'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginForm("lida24@gmail.com", "daT45#li");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }
}