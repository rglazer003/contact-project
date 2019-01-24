import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Delete {
    public static void deleteContact(List<contact> contactList) throws IOException {
        Path contactsPath = Paths.get(contact.directory, contact.filename);
        List<String> contacts = new ArrayList<>();
        contact.showContacts(false, contactList);
        int userInput = Input.getInt(1, contactList.size(), "Enter the Index number of the contact you want to delete: ");
        contactList.remove(userInput - 1);
        for (final contact entry : contactList) {
            String hold1 = entry.name;
            String hold2 = entry.number;
            String hold3 = entry.email;
            contacts.add(hold1);
            contacts.add(hold2);
            contacts.add(hold3);
        }
        Files.write(contactsPath, contacts);
        contact.menu();
    }
}
