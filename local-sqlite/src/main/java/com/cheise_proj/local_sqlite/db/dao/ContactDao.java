package com.cheise_proj.local_sqlite.db.dao;

import com.cheise_proj.local_sqlite.model.ContactLocal;

import java.util.List;

import io.reactivex.Single;

public interface ContactDao {
    Single<Integer> createContact(int userId, String name, String contact, String email);

    Single<List<ContactLocal>> getAllContacts(int userId);

    Single<ContactLocal> getContact(int userId, int contactId);
}
