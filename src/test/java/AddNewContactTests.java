import models.Contact;
import models.User;
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

    @Test
    public void addContactSuccess(){
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

    }
}
