import util.Input;

import java.io.IOException;
import java.util.List;
public class Search {
    public static void searchContacts(List<contact> contactList) throws IOException {
        String userInput = Input.getString("Enter a name to view the phone number for a particular contact:");
        userInput = userInput.toLowerCase();
        for (final contact entry : contactList) {
            if (entry.name.toLowerCase().contains(userInput)) {
                System.out.println(entry.name + " | " + entry.number + " | " + entry.email);
            }
        }
        System.out.println();
        System.out.println("Press enter to continue");
        Input.waitForEnter();
        contact.menu();
    }
}
