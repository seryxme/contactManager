package com.hotnigeria.contactManager;

import com.hotnigeria.contactManager.controllers.UserController;
import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static UserController userController = new UserController();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        mainMenu();
    }

    private static void mainMenu() {
        String mainMenu = """
                Contact Manager App
                1. Create Account
                2. Add Contact
                3. Find Contact
                4. Edit Contact
                5. Delete Contact
                """;
        switch (userInput(mainMenu)) {
            case "1" -> createAccount();
            case "2" -> addContact();
            case "3" -> findContact();
            case "4" -> editContact();
            case "5" -> deleteContact();
        }
    }


    private static String userInput(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    private static void createAccount() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName(userInput("Full Name: "));
        request.setPhoneNumber(userInput("Phone Number: "));
        request.setUsername(userInput("Username: "));
        request.setEmail(userInput("Email address:"));

        userController.registerUser(request);
        mainMenu();
    }

    private static void addContact() {
        AddContactRequest contactRequest = new AddContactRequest();
        contactRequest.setFirstName(userInput("Contact First Name: "));
        contactRequest.setLastName(userInput("Contact Last Name: "));
        contactRequest.setPhoneNumber(userInput("Contact Phone Number: "));
        contactRequest.setEmail(userInput("Contact Email: "));
        contactRequest.setUserEmail(userInput("Your Email: "));

        userController.addContact(contactRequest);
        mainMenu();
    }

    private static void findContact() {
        var contacts = userController.findContactBelongingTo(userInput("Enter your email: "));
        for(Contact contact: contacts) {
            System.out.println(contact);
        }
        mainMenu();
    }

    private static void editContact() {
        var contacts = userController.findContactBelongingTo(userInput("Enter your email: "));
        String contactName = userInput("Enter contact name: ");
        for(Contact contact: contacts) {
            System.out.println(contact);
        }
    }

    private static void deleteContact() {

    }

}
