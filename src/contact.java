
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;


public class contact {
    static Input input = new Input();
    static String directory = "src/data";
    static String filename = "contacts.txt";
    static String testFilename = "testContacts.txt";



    public static void main(String[] args) throws IOException {
//        menu();
//        addContactsTest();
        showContactsTest();
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

    public static void showContactsTest() throws IOException {
        Path contactsPath = Paths.get(directory,testFilename);
        List<String> contacts = Files.readAllLines(contactsPath);
        String hold1 = "";
        String hold2 = "";
        int track = 0;
        int count = 0;
        for (String contact : contacts){
            if (track%2==1){
                hold2 = contact;
                count++;
            }
            else {
                hold1 = contact;
                count++;
            }
            if (count == 2){
                System.out.println(hold1+ " | "+hold2);
                count=0;
            }
            track++;

        }
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

    public static void addContactsTest() throws IOException {

        Path contactsPath = Paths.get(directory, testFilename);
        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

//        List<HashMap> contactList = Files.readAllLines(Paths.get(directory, filename));
        List<String> contactList = Files.readAllLines(Paths.get(directory, filename));
        String contactName = input.getString("Please enter new contact name: ");
        String contactNumber = input.getString("Please enter contact phone number: ");

        contactList.add(contactName);
        contactList.add(contactNumber);
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
