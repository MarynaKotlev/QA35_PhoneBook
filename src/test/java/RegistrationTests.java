import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCond() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("test"+i+"@gmail.com", "MarinaTest_123");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationNegativeWrongEmailFormat() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(new User().setEmail("marinatestgmail.com").setPassword("MarinaTest_123"));
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorWrongFormat());
    }

    @Test
    public void registrationNegativeUserExists(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("marinatest@gmail.com", "MarinaTest_123");
        app.getHelperUser().submitRegistration();
        app.getHelperUser().pause(2000);
        Assert.assertTrue(app.getHelperUser().isAlertPresent());
        Assert.assertTrue(app.getHelperUser().isErrorUserExists());
    }
}
