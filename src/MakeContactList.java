import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MakeContactList {
    public static List<contact> makeContactList() throws IOException {
        Path contactsPath = Paths.get(contact.directory, contact.filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        List<contact> contactList = new ArrayList<>();
        String hold1 = "";
        String hold2 = "";
        String hold3 = "";
        int track = 1;
        int count = 0;
        for (String contact : contacts) {
            if (track == 1) {
                hold1 = contact;
                count++;
            } else if (track == 2) {
                hold2 = contact;
                count++;
            } else if (track == 3) {
                hold3 = contact;
                count++;
            }
            track++;
            if (count == 3) {
                contact entry = new contact(hold1, hold2, hold3);
                contactList.add(entry);
                count = 0;
                track = 1;
            }
        }
        return contactList;
    }
}
