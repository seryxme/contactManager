package com.hotnigeria.contactManager;

import com.hotnigeria.contactManager.controllers.UserController;
import com.hotnigeria.contactManager.data.models.Contact;
import com.hotnigeria.contactManager.dtos.requests.AddContactRequest;
import com.hotnigeria.contactManager.dtos.requests.DeleteContactRequest;
import com.hotnigeria.contactManager.dtos.requests.FindContactRequest;
import com.hotnigeria.contactManager.dtos.requests.RegisterRequest;
import com.hotnigeria.contactManager.utils.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static UserController userController = new UserController();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);

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
            case "3" -> findAllUserContacts();
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

    private static void findAllUserContacts() {
        var contacts = userController.findContactBelongingTo(userInput("Enter your email: "));
        for(Contact contact: contacts) {
            System.out.println(contact);
        }
        mainMenu();
    }

    private static void editContact() {
        AddContactRequest addRequest = new AddContactRequest();
        Mapper.map(findContact(), addRequest);

        String prompt = """
                1. Edit Contact First Name
                2. Edit Contact Last Name
                3. Edit Contact Phone Number
                4. Edit Contact Email
                """;

        switch(userInput(prompt)) {
            case "1"-> addRequest.setFirstName(userInput("Enter New First Name: "));
            case "2"-> addRequest.setLastName(userInput("Enter New Last Name: "));
            case "3"-> addRequest.setPhoneNumber(userInput("Enter New Phone Number: "));
            case "4"-> addRequest.setEmail(userInput("Enter New Email: "));
        }

        addRequest.setUserEmail(userInput("Enter your email: "));

        userController.addContact(addRequest);
        mainMenu();
    }

    private static void deleteContact() {
        DeleteContactRequest deleteRequest = new DeleteContactRequest();
        Mapper.map(findContact(), deleteRequest);

        deleteRequest.setUserEmail(userInput("Enter your email: "));
        userController.deleteContact(deleteRequest);
        mainMenu();
    }

    private static Contact findContact() {
        FindContactRequest request = new FindContactRequest();
        request.setUserEmail(userInput("Enter your email: "));
        request.setContactDetail(userInput("Enter contact name or phone number: "));
        var foundContacts = userController.findContactByDetail(request).getFoundContacts();
        int listNumber = 1;
        for(Contact contact: foundContacts) {
            System.out.printf("%d. %s%n", listNumber, contact);
        }
        int contactListNumber = Integer.parseInt(userInput("Enter list number of the contact: "));
        return foundContacts.get(contactListNumber-1);
    }

}
