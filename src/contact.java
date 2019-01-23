
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class contact {
    private String name;
    private String number;
    static Input input = new Input();
    static String directory = "src/data";
    static String filename = "contacts.txt";


    public static void main(String[] args) {
        menu();
    }


    public static void showContacts(boolean menu) throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        List<contact> contactList = new ArrayList<>();
        String hold1 = "";
        String hold2 = "";
        int track = 0;
        int count = 0;
        int number = 1;
        for (String contact : contacts) {
            if (track % 2 == 1) {
                hold2 = contact;
                count++;
            } else {
                hold1 = contact;
                count++;
            }
            if (count == 2) {
                contact entry = new contact(hold1,hold2);
                contactList.add(entry);
                count = 0;
            }
            track++;

        }
        System.out.println("------ | --------------- | -------------");
        System.out.format("%-6s | %-15s | %-5s%n", "Index", "Contact Name", "Phone Number");
        System.out.println("------ | --------------- | -------------");
        for (contact entry: contactList){
            System.out.format("%-6s | %-15s | %-5s%n", number, entry.name, entry.number);
            number++;
        }
        System.out.println();

        if (menu) {
            System.out.println("Press Enter to continue");
            input.waitForEnter();
            menu();
        }
    }

    public contact (String name, String number){
        this.name = name;
        this.number = number;
    }

    public static void addContacts() throws IOException {

        Path contactsPath = Paths.get(directory, filename);
        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> contactList = Files.readAllLines(Paths.get(directory, filename));
        String contactName = input.getString("Please enter new contact name: ");

        for(String contact: contactList){
            if(contactName.equals(contact)){
                System.out.println("Contact already exists, do you want to overwrite?");
                boolean confirm = input.yesNo();
                if(confirm){
                    int index = contactList.indexOf(contactName);
                    contactList.remove(index);
                    contactList.remove(index);
                    break;
                }else{
                    addContacts();
                }
            }
        }
        String contactNumber = input.getString("Please enter contact phone number: ");
        contactList.add(contactName);
        String number = "";
        if(contactNumber.length() == 7){
            number = contactNumber.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        }else if(contactNumber.length() == 10){
            number = contactNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
        }else{
            number = contactNumber;
        }
            contactList.add(number);
        Files.write(contactsPath, contactList);
        menu();
    }

    public static void searchContacts() throws IOException {
        String userInput = input.getString("Enter a name to view the phone number for a particular contact:");
        userInput = userInput.toLowerCase();
        Path contactsPath = Paths.get(directory, filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        String hold1 = "";
        String hold2 = "";
        int count = 0;
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(userInput)) {
                hold1 = contact;
                hold2 = contacts.get(count + 1);
                System.out.println(hold1 + " | " + hold2);
            }
            count++;

        }
        menu();
    }

    public static void deleteContact() throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        showContacts(false);
        int userInput = input.getInt(1, (contacts.size() / 2), "Enter the Index number of the contact you want to delete: ");
        if (userInput == 1){
            contacts.remove(userInput-1);
            contacts.remove(userInput-1);
        }
        else{
            contacts.remove(userInput+(userInput-2));
            contacts.remove(userInput+(userInput-2));
        }
        Files.write(contactsPath, contacts);
        menu();

    }


    public static void menu() {
        System.out.println("Welcome! What would you like to do?");

        System.out.println("1: Show all your contacts\n2: Add a new contact\n3: Search a contact by her name\n4: Delete an existing contact\n0: Exit");
        int userChoice = input.getInt(0, 4);

        input.waitForEnter();

        if (userChoice == 1) {
            try {
                showContacts(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 2) {
            try {
                addContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 3) {
            try {
                searchContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 4) {
            try {
                deleteContact();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (userChoice == 0) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }
}
