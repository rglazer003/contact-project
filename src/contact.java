
import util.Input;

import java.io.IOException;
import java.util.List;


public class contact {
    protected String name;
    protected String number;
    protected String email;
    static String directory = "src/data";
    static String filename = "contacts.txt";
    static String testfilename = "testContacts.txt";
    static List<contact> contactList;


    public static void main(String[] args) {
        menu();
    }


    public static void showContacts(boolean menu, List<contact> contactList) throws IOException {
        int number = 1;
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
            Input.waitForEnter();
            menu();
        }
    }

    public contact(String name, String number, String email) throws IOException {
        this.name = name;
        this.number = number;
        this.email = email;
    }


    public static void menu() {
        try {
            contactList =
                    MakeContactList.makeContactList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome! What would you like to do?");

        System.out.println("1: Show all your contacts\n2: Add a new contact\n3: Search a contact by their name\n4: Delete an existing contact\n5: Edit a contact\n0: Exit");
        int userChoice = Input.getInt(0, 5);

        Input.waitForEnter();

        if (userChoice == 1) {
            try {
                showContacts(true, contactList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 2) {
            try {
                AddContact.addContacts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 3) {
            try {
                Search.searchContacts(contactList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (userChoice == 4) {
            try {
                Delete.deleteContact(contactList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (userChoice == 5){
            try {
                EditContact.editContacts(contactList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (userChoice == 0) {
            System.out.println("Goodbye");
            System.exit(0);
        }
    }
}
