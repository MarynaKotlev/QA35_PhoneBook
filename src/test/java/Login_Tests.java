import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_Tests extends TestBase {

    @BeforeMethod
    public void preCond() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("marinatest@gmail.com", "MarinaTest_123");
        app.getHelperUser().submitLogin();
    }


}