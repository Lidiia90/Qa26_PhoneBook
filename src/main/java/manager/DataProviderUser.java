package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"kate24@gmail.com", "kaT45#kit"});
        list.add(new Object[]{"sonya@gmail.com", "Ss12345$"});
        list.add(new Object[]{"mara@gmail.com", "Mmar123456$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModels() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("kate24@gmail.com").withPassword("kaT45#kit")});
        list.add(new Object[]{new User().withEmail("sonya@gmail.com").withPassword("Ss12345$")});
        return list.iterator();
    }
}
