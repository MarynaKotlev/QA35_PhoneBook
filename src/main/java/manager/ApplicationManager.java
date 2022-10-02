package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    WebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;

    public void init() {
        wd = new ChromeDriver();
        //раскрыть браузер на весь экран:
        wd.manage().window().maximize();

        // ожидание после неудачного поиска, перед выдачей ошибки
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/");

        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
    }

    public void stop() {
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact(){
        return helperContact;
    }
}
