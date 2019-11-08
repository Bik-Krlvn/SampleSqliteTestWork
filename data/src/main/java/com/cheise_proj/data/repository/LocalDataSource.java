package com.cheise_proj.data.repository;

import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.data.model.UserData;

import java.util.List;

import io.reactivex.Single;

public interface LocalDataSource {
    Single<Integer> createUser(String username, String email, String password, String imagePath);

    Single<UserData> getAuthenticatedUser(String username, String password);

    Single<Integer> createContact(int userId, String name, String contact, String email);

    Single<List<ContactData>> getAllContacts(int userId);

    Single<ContactData> getContact(int userId, int contactId);
}
