import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AddContact {
    public static void addContacts() throws IOException {

        Path contactsPath = Paths.get(contact.directory, contact.filename);
        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> contactList = Files.readAllLines(Paths.get(contact.directory, contact.filename));
        String contactName = Input.getString("Please enter new contact name: ");

        for (String contact : contactList) {
            if (contactName.equals(contact)) {
                System.out.println("Contact already exists, do you want to overwrite?");
                boolean confirm = Input.yesNo();
                if (confirm) {
                    int index = contactList.indexOf(contactName);
                    contactList.remove(index);
                    contactList.remove(index);
                    break;
                } else {
                    addContacts();
                }
            }
        }
        String contactNumber = Input.getString("Please enter contact phone number: ");
        contactList.add(contactName);

        String number = "";
        if (contactNumber.length() == 7) {
            number = contactNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (contactNumber.length() == 10) {
            number = contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        } else {
            number = contactNumber;
        }
        contactList.add(number);
        boolean loop = true;
        while(loop) {
            String contactEmail = Input.getString("Please enter contact email address: ");
            if (contactEmail.contains("@") && contactEmail.contains(".")) {
                contactList.add(contactEmail);
                Files.write(contactsPath, contactList);
                loop = false;
            } else {
                System.out.println("Not a valid email.");
            }
        }
                contact.menu();
    }

}
