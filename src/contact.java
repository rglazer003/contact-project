
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class contact {
    static Input input = new Input();
    static String directory = "src/data";
    static String filename = "contacts.txt";

    public static void main(String[] args) {
        try {
            showContacts();
            addContacts();
            showContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void showContacts() throws IOException {
        Path contactsPath = Paths.get(directory,filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        for (String contact : contacts){
            System.out.println(contact);
        }
    }

    public static void addContacts() throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        if(Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> contactList = Files.readAllLines(Paths.get(directory, filename));

        String contact = input.getString("Please enter new contact: ");

        contactList.add(contact);

        Files.write(contactsPath, contactList);
    }
}
