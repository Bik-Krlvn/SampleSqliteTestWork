package com.cheise_proj.local_sqlite.source;

import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.data.model.UserData;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.local_sqlite.db.dao.ContactDao;
import com.cheise_proj.local_sqlite.db.dao.UserDao;
import com.cheise_proj.local_sqlite.mapper.user.ContactDataLocalMapper;
import com.cheise_proj.local_sqlite.mapper.user.UserDataLocalMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalDataSourceImpl implements LocalDataSource {
    private UserDao userDao;
    private UserDataLocalMapper userDataLocalMapper;
    private ContactDao contactDao;
    private ContactDataLocalMapper contactDataLocalMapper;

    /**
     * Constructor
     *
     * @param userDao                provide user dao
     * @param userDataLocalMapper    provide user data local mapper instance
     * @param contactDao             provide contact dao
     * @param contactDataLocalMapper provide contact data local mapper instance
     */
    @Inject
    public LocalDataSourceImpl(UserDao userDao,
                               UserDataLocalMapper userDataLocalMapper,
                               ContactDao contactDao,
                               ContactDataLocalMapper contactDataLocalMapper) {

        this.userDao = userDao;
        this.userDataLocalMapper = userDataLocalMapper;
        this.contactDao = contactDao;
        this.contactDataLocalMapper = contactDataLocalMapper;
    }

    @Override
    public Single<Integer> createUser(String username, String email, String password, String imagePath) {
        return userDao.createUser(username, email, password, imagePath);
    }

    @Override
    public Single<UserData> getAuthenticatedUser(String username, String password) {
        return userDao.getUser(username, password)
                .map(userLocal -> userDataLocalMapper.from(userLocal));
    }

    @Override
    public Single<UserData> getUserById(int userId) {
        return userDao.getUserById(userId)
                .map(userLocal -> userDataLocalMapper.from(userLocal));
    }

    @Override
    public Single<Integer> createContact(int userId, String name, String contact, String email) {
        return contactDao.createContact(
                userId,
                name,
                contact,
                email
        );
    }

    @Override
    public Single<List<ContactData>> getAllContacts(int userId) {
        return contactDao.getAllContacts(userId)
                .map(contactLocalList -> contactDataLocalMapper.fromList(contactLocalList));
    }

    @Override
    public Single<ContactData> getContact(int userId, int contactId) {
        return contactDao.getContact(userId, contactId)
                .map(contactLocal -> contactDataLocalMapper.from(contactLocal));
    }
}
