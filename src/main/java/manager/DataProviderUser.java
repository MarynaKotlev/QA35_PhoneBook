package manager;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"marinatest@gmail.com", "MarinaTest_123"});
        list.add(new Object[]{"marinatest@gmail.com", "MarinaTest_123"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> registrationDataSuccess() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dataRegSuccess.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}

/*
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }
 */