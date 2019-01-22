
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
        menu();
    }

    public static void showContacts() throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        for (String contact : contacts) {
            System.out.println(contact);
        }
        System.out.println("Press enter to go back to menu.");
        input.waitForEnter();
        menu();
    }

    public static void addContacts() throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> contactList = Files.readAllLines(Paths.get(directory, filename));

        String contact = input.getString("Please enter new contact: ");

        contactList.add(contact);

        Files.write(contactsPath, contactList);
    }

    public static void menu() {
        System.out.println("Welcome! What would you like to do?");

        System.out.println("1: Show all your contacts\n2: Add a new contact\n3:Search a contact by her name\n4:Delete an existing contact\n0:Exit");
        int userChoice = input.getInt(0, 5);

        input.waitForEnter();

        if (userChoice == 1) {
            try {
                showContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
