package com.cheise_proj.data.utils;

import com.cheise_proj.data.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * TestContactGenerator
 */
public class TestContactGenerator {
    public static List<ContactData> getAllContacts() {
        List<ContactData> contactEntityList = new ArrayList<>();
        contactEntityList.add(
                new ContactData(1, 1, "test contact name1", "201010", "contact email-1")
        );
        contactEntityList.add(
                new ContactData(2, 1, "test contact name2", "2010102", "contact email-2")
        );
        return contactEntityList;
    }

    public static ContactData getContact() {
        return new ContactData(1, 1, "test contact name1", "201010", "contact email-1");
    }

    public static ContactData createContact() {
        return new ContactData(1, 1, "test contact name1", "201010", "contact email-1");
    }
}
