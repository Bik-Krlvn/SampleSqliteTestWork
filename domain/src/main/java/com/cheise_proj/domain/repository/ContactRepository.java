package com.cheise_proj.domain.repository;

import com.cheise_proj.domain.entity.ContactEntity;

import java.util.List;

import io.reactivex.Observable;

public interface ContactRepository {
    Observable<Integer> createContact(int userId, String name, String contact, String email);

    Observable<List<ContactEntity>> getAllContacts(int userId);

    Observable<ContactEntity> getContact(int userId, int contactId);

}
