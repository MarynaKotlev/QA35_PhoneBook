package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class HelperContact extends HelperBase{

public HelperContact (WebDriver wd) {
super(wd);
}


    public void openAddContactForm() {
    wd.findElement(By.cssSelector("a[href='/add']")).click();
    }

    public void fillContactForm(Contact contact) {
    type(By.cssSelector("input[placeholder='Name']"), contact.getName());
    type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
    type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
    type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
    type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
    type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
    wd.findElement(By.cssSelector(".add_form__2rsm2>button")).click();
    }


    public int contactsCount() {
    wd.findElement(By.cssSelector("a[href='/contacts']")).click();
    return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }


    public boolean isAddContactFormOpen() {
    return wd.findElements(By.cssSelector("a[href='/add'].active")).size() == 1;
    }

    public void openFirstContact() {
    wd.findElement(By.cssSelector(".contact-item_card__2SOIM:first-child")).click();
    }

    public void removeContact() {
    wd.findElement(By.xpath("//button[text()='Remove']")).click();
    }

    public String getMessage() {
    return wd.findElement(By.cssSelector("div[class*='contact-page_message']>h1")).getText();
    }

    public void openContactList() {
    wd.findElement(By.cssSelector("a[href='/contacts']")).click();
    }

    public void providerOfContact() {
    int count = 0;
    Random random = new Random();


    while(count != 3) {
        int i = random.nextInt(1000)+100;
        Contact contact = Contact.builder()
                .name("Name"+i)
                .lastName("Dow")
                .phone("254-112-455"+i)
                .email("name"+i+"@mail.com")
                .address("Netanya, Street")
                .description("Here is the description")
                .build();
        openAddContactForm();
        fillContactForm(contact);
        pause(1000);
        saveContact();
        pause(1000);
        count += 1;
    }
    }
}
