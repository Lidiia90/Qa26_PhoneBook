package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate24@gmail.com", "kaT45#kit");
        app.getHelperUser().submitLogin();

        // Assert.assertEquals();
        // Assert.assertNotEquals();
        // Assert.assertTrue();
        // Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }
}
