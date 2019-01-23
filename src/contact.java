
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
    private String email;
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
        String hold3 = "";
        int track = 1;
        int count = 0;
        int number = 1;
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
        System.out.println("------ | --------------- | ------------ | --------------");
        System.out.format("%-6s | %-15s | %-5s | %-40s%n", "Index", "Contact Name", "Phone Number", "Email Address");
        System.out.println("------ | --------------- | ------------ | --------------");
        String phoneNumber;
        for (contact entry : contactList) {
            if (entry.number.length() == 8) {
                phoneNumber = entry.number + "    ";
            } else {
                phoneNumber = entry.number;
            }
            System.out.format("%-6s | %-15s | %-5s | %-40s%n", number, entry.name, phoneNumber, entry.email);
            number++;
        }
        System.out.println();

        if (menu) {
            System.out.println("Press Enter to continue");
            input.waitForEnter();
            menu();
        }
    }

    public contact(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public static void addContacts() throws IOException {

        Path contactsPath = Paths.get(directory, filename);
        if (Files.notExists(contactsPath)) {
            Files.createFile(contactsPath);
        }

        List<String> contactList = Files.readAllLines(Paths.get(directory, filename));
        String contactName = input.getString("Please enter new contact name: ");

        for (String contact : contactList) {
            if (contactName.equals(contact)) {
                System.out.println("Contact already exists, do you want to overwrite?");
                boolean confirm = input.yesNo();
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
        String contactNumber = input.getString("Please enter contact phone number: ");
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
        String contactEmail = input.getString("Please enter contact email address: ");
        contactList.add(contactEmail);
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
        String hold3 = "";
        int count = 0;
        for (String contact : contacts) {
            if (contact.toLowerCase().contains(userInput)) {
                if(count > contacts.size()){
                    count = contacts.size();
                }
                hold1 = contact;
                hold2 = contacts.get(count + 1);
                hold3 = contacts.get(count + 2);
                System.out.println(hold2);
                System.out.println(hold1 + " | " + hold2 + " | " + hold3);
            }
            count++;

        }
        System.out.println();
        System.out.println("Press enter to continue");
        input.waitForEnter();
        menu();
    }

    public static void deleteContact() throws IOException {
        Path contactsPath = Paths.get(directory, filename);
        List<String> contacts = Files.readAllLines(contactsPath);
        showContacts(false);
        int userInput = input.getInt(1, (contacts.size() / 3), "Enter the Index number of the contact you want to delete: ");
        if (userInput == 1) {
            contacts.remove(userInput - 1);
            contacts.remove(userInput - 1);
            contacts.remove(userInput - 1);
        } else {
            contacts.remove(userInput + (-3 + (2 * userInput)));
            contacts.remove(userInput + (-3 + (2 * userInput)));
            contacts.remove(userInput + (-3 + (2 * userInput)));
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
