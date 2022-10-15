import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void preCond(){
        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("marinatest@gmail.com").setPassword("MarinaTest_123"));
        }
    }

    @Test (invocationCount = 3)
    public void addContactSuccess(){
        int countBefore = app.getHelperContact().contactsCount();
        Random random = new Random();
        int i = random.nextInt(1000)+100;

        Contact contact = Contact.builder()
                .name("Name"+i)
                .lastName("Dow")
                .phone("254-112-455"+i)
                .email("name"+i+"@mail.com")
                .address("Netanya, Street")
                .description("Here is the description")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(1000);
        app.getHelperContact().saveContact();
        app.getHelperContact().pause(1000);
        int countAfter = app.getHelperContact().contactsCount();
        Assert.assertEquals(countAfter, countBefore+1);

    }

    @Test
    public void addContactFailEmptyField(){

        Random random = new Random();
        int i = random.nextInt(1000)+100;

        Contact contact = Contact.builder()
                .name("")
                .lastName("Dow")
                .phone("254-112-455"+i)
                .email("name"+i+"@mail.com")
                .address("Netanya, Street")
                .description("Here is the description")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(1000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddContactFormOpen());
    }
}
