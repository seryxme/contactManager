package com.hotnigeria.contactManager;

import com.hotnigeria.contactManager.controllers.UserController;
import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;

import java.util.Scanner;

public class Main {
    private static UserController userController = new UserController();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        String mainMenu = """
                Contact Manager App
                1. Create Account
                2. Add Contact
                3. Find Contact
                """;
        switch (userInput(mainMenu)) {
            case "1" -> createAccount();
            case "2" -> addContact();
            case "3" -> findContact();
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
}
