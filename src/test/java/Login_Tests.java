import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Login_Tests extends TestBase {

    @BeforeMethod
    public void preCond() {
        logger.info("START checking authorization");
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Test was needed in Logout");
        } else {
            logger.info("Test was NOT needed in Logout");
        }
    }

    @Test (dataProvider = "loginData", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        logger.info("User logins with data ---> email: " + email + " password: " + password);
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert passed (Sign Out button is present)");
    }

    @Test
    public void loginSuccessModel() {

        User user = new User().setEmail("marinatest@gmail.com").setPassword("MarinaTest_123");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginNegativeWrongEmailFormat() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(new User().setEmail("marinatestgmail.com").setPassword("MarinaTest_123"));
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());
    }


}