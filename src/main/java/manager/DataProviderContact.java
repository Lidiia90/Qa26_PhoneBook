package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .Name("Lora")
                .LastName("Gam")
                .Phone("1234567823")
                .email("lora@gmail.com")
                .Address("Tel aviv, Israel")
                .description("wrong email")
                .build()});
        list.add(new Object[]{Contact.builder()
                .Name("LiReq")
                .LastName("Gam")
                .Phone("0987654321")
                .email("liR@gmail.com")
                .Address("Tel aviv, Israel")
                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{Contact.builder()
                .Name("John")
                .LastName("Wick")
                .Phone("1238965125698745622322555")
                .email("wick1@gmail.com")
                .Address("NY")
                .description("John")
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name("John")
                .LastName("Wick")
                .Phone("wwwwwwwwwwww")
                .email("wick1@gmail.com")
                .Address("NY")
                .description("John")
                .build()});

        list.add(new Object[]{Contact.builder()
                .Name("John")
                .LastName("Wick")
                .Phone("")
                .email("wick1@gmail.com")
                .Address("NY")
                .description("John")
                .build()});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while(line!=null){
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .Name(all[0])
                    .LastName(all[1])
                    .email(all[2])
                    .Phone(all[3])
                    .Address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
