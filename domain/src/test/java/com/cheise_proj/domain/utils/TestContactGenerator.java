package com.cheise_proj.domain.utils;

import com.cheise_proj.domain.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * TestContact Generator, provide dummy data for Contact test
 */
public class TestContactGenerator {
    public static List<ContactEntity> getAllContacts() {
        List<ContactEntity> contactEntityList = new ArrayList<>();
        contactEntityList.add(
                new ContactEntity(1, 1, "test contact name1", "201010", "contact email-1")
        );
        contactEntityList.add(
                new ContactEntity(2, 1, "test contact name2", "2010102", "contact email-2")
        );
        return contactEntityList;
    }

    public static ContactEntity getContact() {
        return new ContactEntity(1, 1, "test contact name1", "201010", "contact email-1");
    }

    public static ContactEntity createContact() {
        return new ContactEntity(1, 1, "test contact name1", "201010", "contact email-1");
    }
}
