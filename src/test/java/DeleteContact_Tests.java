import com.beust.ah.A;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContact_Tests extends TestBase{
    @BeforeMethod
    public void preCond(){
        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("marinatest@gmail.com").setPassword("MarinaTest_123"));
        }
        app.getHelperContact().openContactList();
        if(app.getHelperContact().contactsCount() < 4) {
            app.getHelperContact().providerOfContact();
        }
    }

    @Test
    public void deleteFirstContact(){
        int countBefore = app.getHelperContact().contactsCount();
        app.getHelperContact().openFirstContact();
        app.getHelperContact().removeContact();
        app.getHelperContact().pause(1000);
        int countAfter = app.getHelperContact().contactsCount() + 1;

        Assert.assertEquals(countBefore, countAfter);

    }

    @Test
    public void deleteAllContacts(){

        while (app.getHelperContact().contactsCount() > 0) {
            app.getHelperContact().openFirstContact();
            app.getHelperContact().pause(2000);
            app.getHelperContact().removeContact();
            app.getHelperContact().pause(2000);
        }

        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
    }
}
