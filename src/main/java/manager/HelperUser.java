package manager;

import models.User;
import org.openqa.selenium.Alert;
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
       // WebElement loginTab = wd.findElement(By.xpath("//a[@href='/login']"));
        WebElement loginTab = wd.findElement(By.xpath("//a[@href='/login']"));
        loginTab.click();

    }


    public void fillLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[@placeholder='Email']"), email);
        type(By.xpath("//input[@placeholder='Password']"), password);

    }

    public void fillLoginRegistrationForm(User user) {
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());

    }

    public void submitLogin() {
        WebElement loginButton = wd.findElement(By.xpath("//button[1]"));
        // второй вариант - //button[text()=" Login"]
        // //*[text()=" Login"]

        loginButton.click();
    }

    public boolean isAlertPresent() {
        Alert alert = wd.switchTo().alert(); //переключить фокус веб драйвера на окно алерта
        if (alert == null) return false;
        return true;
    }

    public boolean isErrorWrongFormat() {
        Alert alert = wd.switchTo().alert();
        String errorText = alert.getText(); //получить текст алерта
        alert.accept(); //клик на кнопку ОК
        // alert.dismiss(); - клик на кнопку cancel
        // alert.sendKeys("text"); - если в алерт нужно вписать текст
        return errorText.contains("Wrong email or password format");
    }

    public boolean isErrorUserExists() {
        Alert alert = wd.switchTo().alert();
        String errorText = alert.getText();
        alert.accept();
        return errorText.contains("User already exist");
    }


    public void submitRegistration() {
        wd.findElement(By.xpath("//button[2]")).click();
    }

    public void login(User user){
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
