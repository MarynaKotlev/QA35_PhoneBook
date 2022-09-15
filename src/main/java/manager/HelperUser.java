package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        return list.size() > 0;
    }

    public void logout() {
        wd.findElement(By.xpath("//button[text()='Sign Out']")).click();

    }

    public void openLoginRegistrationForm() {
        // один слеш / для абсолютного пути, два слеша // для относительного
        // в Хpath все атрибуты пишутся в [] и начинаются с @
        WebElement loginTab = wd.findElement(By.xpath("//a[@href='/login']"));
        loginTab.click();

    }


    public void fillLoginRegistrationForm(String email, String password) {
        WebElement inputEmail = wd.findElement(By.xpath("//input[@placeholder='Email']"));
        inputEmail.click(); // ставим курсор в поле для ввода
        inputEmail.clear(); // очистить поле перед тем, как печатать в нем
        inputEmail.sendKeys(email);

        WebElement inputPassword = wd.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(password);

    }

    public void submitLogin() {
        WebElement loginButton = wd.findElement(By.xpath("//button[1]"));
        // второй вариант - //button[text()=" Login"]
        // //*[text()=" Login"]

        loginButton.click();
    }
}
