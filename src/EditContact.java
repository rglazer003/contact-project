import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EditContact {
    public static void editContacts (List<contact> contactList) throws IOException {
        Path contactsPath = Paths.get(contact.directory, contact.filename);
        List<String> contacts = new ArrayList<>();
        try {
            contact.showContacts(false, contactList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Which contact would you like to edit?");
        int userinput = Input.getInt(1,contactList.size());
        Input.waitForEnter();
        String name = Input.getString("Please enter new name");
        String contactNumber = Input.getString("Please enter new phone number.");
        String number = "";
        if (contactNumber.length() == 7) {
            number = contactNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (contactNumber.length() == 10) {
            number = contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        } else {
            number = contactNumber;
        }
        boolean loop = true;
        String email = "";
        while (loop){
            email = Input.getString("Please enter new email.");
            if (email.contains("@")&&email.contains(".")){
                loop = false;
            }else {
                System.out.println("Not a valid email address.");
            }
        }
        contact editedContact = new contact(name,number,email);
        contactList.set(userinput-1,editedContact);
        for (final contact entry : contactList){
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
