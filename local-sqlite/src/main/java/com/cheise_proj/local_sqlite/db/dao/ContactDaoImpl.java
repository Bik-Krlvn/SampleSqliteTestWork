package com.cheise_proj.local_sqlite.db.dao;

import com.cheise_proj.local_sqlite.db.AppLocalDatabase;
import com.cheise_proj.local_sqlite.model.ContactLocal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * ContactDao impl class
 * class implements contact dao
 */
public class ContactDaoImpl implements ContactDao {
    private AppLocalDatabase appLocalDatabase;

    /**
     * Constructor
     *
     * @param appLocalDatabase require local sqLite db
     */
    @Inject
    public ContactDaoImpl(AppLocalDatabase appLocalDatabase) {
        this.appLocalDatabase = appLocalDatabase;
    }

    @Override
    public Single<Integer> createContact(int userId, String name, String contact, String email) {
        return Single.just(appLocalDatabase.insertContact(
                userId,
                name,
                contact,
                email
        ));
    }

    @Override
    public Single<List<ContactLocal>> getAllContacts(int userId) {
        return Single.just(appLocalDatabase.getAllContacts(userId));
    }

    @Override
    public Single<ContactLocal> getContact(int userId, int contactId) {
        return Single.just(appLocalDatabase.getContactById(userId, contactId));
    }
}
