package com.cheise_proj.presentation.utils;

import com.cheise_proj.presentation.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * TestContactGenerator, provides dummy data for view model test
 */
public class TestContactGenerator {
    public static List<Contact> getAllContacts() {
        List<Contact> contactEntityList = new ArrayList<>();
        contactEntityList.add(
                new Contact(1, 1, "test contact name1", "201010", "contact email-1")
        );
        contactEntityList.add(
                new Contact(2, 1, "test contact name2", "2010102", "contact email-2")
        );
        return contactEntityList;
    }

    public static Contact getContact() {
        return new Contact(1, 1, "test contact name1", "201010", "contact email-1");
    }

    public static Contact createContact() {
        return new Contact(1, 1, "test contact name1", "201010", "contact email-1");
    }
}
